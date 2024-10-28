package com.fa.sonagi.statistics.diaper.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fa.sonagi.record.diaper.repository.PeeRepository;
import com.fa.sonagi.record.diaper.repository.PoopRepository;
import com.fa.sonagi.statistics.common.dto.Times;
import com.fa.sonagi.statistics.diaper.dto.DiaperStatisticsDayForWeekDto;
import com.fa.sonagi.statistics.diaper.dto.DiaperStatisticsResDto;
import com.fa.sonagi.statistics.diaper.dto.DiaperStatisticsWeekResDto;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DiaperStatisticsServiceImpl implements DiaperStatisticsService{

	private final PeeRepository peeRepository;
	private final PoopRepository poopRepository;
	private final int WEEK = 7;

	/**
	 * 일별 통계 계산
	 */
	@Override
	public DiaperStatisticsResDto getDiaperStatisticsDay(Long babyId, LocalDate createdDate) {
		DiaperStatisticsResDto diaperStatisticsResDto = new DiaperStatisticsResDto();

		List<Times> diaperDay = new ArrayList<>();
		// 데이터 조회
		List<Times> pees = peeRepository.findPeeByDay(babyId, createdDate);
		for (Times t : pees)
			diaperDay.add(t);
		List<Times> poops = poopRepository.findPoopByDay(babyId, createdDate);
		for (Times t : poops)
			diaperDay.add(t);
		Collections.sort(diaperDay, Comparator.comparing(Times::getCreatedTime));
		diaperStatisticsResDto.setTimes(diaperDay);

		// 횟수 통계
		Long peeCnt = (long)pees.size();
		diaperStatisticsResDto.setPeeCnt(peeCnt);
		Long poopCnt = (long)poops.size();
		diaperStatisticsResDto.setPoopCnt(poopCnt);

		createdDate = createdDate.minus(1, ChronoUnit.DAYS);

		// 소변 횟수 통계 계산
		Long yesterdayPeeCnt = peeRepository.findPeeCnt(babyId, createdDate);
		Long peeCntPercent = getPercent(peeCnt, yesterdayPeeCnt);
		Long yesterdayPeeCntPercent = getPercent(yesterdayPeeCnt, peeCnt);
		diaperStatisticsResDto.setPeeCntPercent(peeCntPercent);
		diaperStatisticsResDto.setYesterdayPeeCntPercent(yesterdayPeeCntPercent);

		// 대변 횟수 통계 계산
		Long yesterdayPoopCnt = poopRepository.findPoopCnt(babyId, createdDate);
		Long poopCntPercent = getPercent(poopCnt, yesterdayPoopCnt);
		Long yesterdayPoopCntPercent = getPercent(yesterdayPoopCnt, poopCnt);
		diaperStatisticsResDto.setPoopCntPercent(poopCntPercent);
		diaperStatisticsResDto.setYesterdayPoopCntPercent(yesterdayPoopCntPercent);

		return diaperStatisticsResDto;
	}

	/**
	 * 주별 통계 계산
	 */
	@Override
	public DiaperStatisticsWeekResDto getDiaperStatisticsWeek(Long babyId, LocalDate createdDate) {
		LocalDate monday = createdDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
		LocalDate sunday = createdDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
		DiaperStatisticsWeekResDto diaperWeek = new DiaperStatisticsWeekResDto();

		// 일주일 데이터 조회
		Map<LocalDate, List<java.time.LocalTime>> peeForWeek = peeRepository.findPeeForWeek(babyId, monday, sunday);
		Map<LocalDate, List<java.time.LocalTime>> poopForWeek = poopRepository.findPoopForWeek(babyId, monday, sunday);

		// 날짜별 데이터 세팅
		LocalDate writeDay = monday;
		Long peeCnt = 0L;
		Long poopCnt = 0L;
		for (int i = 0; i < WEEK; i++) {
			DiaperStatisticsDayForWeekDto diaperDay = new DiaperStatisticsDayForWeekDto();
			if (peeForWeek.containsKey(writeDay)) {
				for (int j = 0; j < peeForWeek.get(writeDay).size(); j++) {
					Long startTime = (long)(peeForWeek.get(writeDay).get(j).getHour() * 60
											+ peeForWeek.get(writeDay).get(j).getMinute());
					diaperDay.getPees().add(startTime);
					peeCnt++;
				}
			}
			if (poopForWeek.containsKey(writeDay)) {
				for (int j = 0; j < poopForWeek.get(writeDay).size(); j++) {
					Long startTime = (long)(poopForWeek.get(writeDay).get(j).getHour() * 60
											+ poopForWeek.get(writeDay).get(j).getMinute());
					diaperDay.getPoops().add(startTime);
					poopCnt++;
				}
			}

			diaperWeek.getDiaperStatistics().put(writeDay.format(DateTimeFormatter.ofPattern("M/dd")), diaperDay);
			writeDay = writeDay.plusDays(1);
		}

		// 카테고리별 일주일 통계 조회
		diaperWeek.setPeeCnt(peeCnt);
		diaperWeek.setPoopCnt(poopCnt);

		monday = monday.minusWeeks(1);
		sunday = sunday.minusWeeks(1);

		// 소변 횟수 통계 퍼센트 계산
		Long lastWeekPeeCnt = peeRepository.findPeeCntByWeek(babyId, monday, sunday);
		Long peeCntPercent = getPercent(peeCnt, lastWeekPeeCnt);
		Long lastWeekPeeCntPercent = getPercent(lastWeekPeeCnt, peeCnt);
		diaperWeek.setPeeCntPercent(peeCntPercent);
		diaperWeek.setYesterdayPeeCntPercent(lastWeekPeeCntPercent);

		// 대변 횟수 통계 퍼센트 계산
		Long lastWeekPoopCnt = poopRepository.findPoopCntByWeek(babyId, monday, sunday);
		Long poopCntPercent = getPercent(poopCnt, lastWeekPoopCnt);
		Long lastWeekPoopCntPercent = getPercent(lastWeekPoopCnt, poopCnt);
		diaperWeek.setPoopCntPercent(poopCntPercent);
		diaperWeek.setYesterdayPoopCntPercent(lastWeekPoopCntPercent);

		return diaperWeek;
	}

	/**
	 * 퍼센트 계산하기 Long
	 */
	public Long getPercent(Long target, Long opponent) {
		if (target == 0) return 0L;
		else if (target >= opponent) {
			return 100L;
		}
		else {
			return target * 100 / opponent;
		}
	}
}
