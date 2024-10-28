package com.fa.sonagi.statistics.extra.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fa.sonagi.record.extra.repository.ExtraRepository;
import com.fa.sonagi.statistics.common.dto.Times;
import com.fa.sonagi.statistics.extra.dto.ExtraStatisticsDayForWeekDto;
import com.fa.sonagi.statistics.extra.dto.ExtraStatisticsResDto;
import com.fa.sonagi.statistics.extra.dto.ExtraStatisticsWeekResDto;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ExtraStatisticServiceImpl implements ExtraStatisticsService {

	private final ExtraRepository extraRepository;
	private final int WEEK = 7;

	/**
	 * 기타 통계 계산
	 */
	@Override
	public ExtraStatisticsResDto getExtraStatisticsDay(Long babyId, LocalDate createdDate) {
		ExtraStatisticsResDto extraStatisticsResDto = new ExtraStatisticsResDto();

		List<Times> extraDay = new ArrayList<>();
		// 데이터 조회 및 카드 통계
		List<Times> extras = extraRepository.findExtraByDay(babyId, createdDate);
		for (Times t : extras)
			extraDay.add(t);
		extraStatisticsResDto.setTimes(extraDay);
		
		Long extraCnt = (long)extras.size();
		extraStatisticsResDto.setCnt(extraCnt);

		createdDate = createdDate.minus(1, ChronoUnit.DAYS);

		// 기타 횟수 통계 퍼센트 계산
		Long yesterdayExtraCnt = extraRepository.findExtraCnt(babyId, createdDate);
		Long cntPercent = getPercent(extraCnt, yesterdayExtraCnt);
		Long yesterdayCntPercent = getPercent(yesterdayExtraCnt, extraCnt);
		extraStatisticsResDto.setCntPercent(cntPercent);
		extraStatisticsResDto.setYesterdayCntPercent(yesterdayCntPercent);

		return extraStatisticsResDto;
	}

	@Override
	public ExtraStatisticsWeekResDto getExtraStatisticsWeek(Long babyId, LocalDate createdDate) {
		LocalDate monday = createdDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
		LocalDate sunday = createdDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
		ExtraStatisticsWeekResDto extraWeek = new ExtraStatisticsWeekResDto();

		// 일주일 데이터 조회
		Map<LocalDate, Long> extras = extraRepository.findExtraCnt(babyId, monday, sunday);

		// 날짜별 데이터 세팅
		LocalDate writeDay = monday;
		for (int i = 0; i < WEEK; i++) {
			ExtraStatisticsDayForWeekDto extraDay = new ExtraStatisticsDayForWeekDto();

			if (extras.containsKey(writeDay))
				extraDay.setCnt(extras.get(writeDay));
			else
				extraDay.setCnt(0L);

			extraWeek.getExtraStatistics().put(writeDay.format(DateTimeFormatter.ofPattern("M/dd")), extraDay);
			writeDay = writeDay.plusDays(1);
		}

		// 일주일 통계 설정
		Long cnt = getStatisticsWeek(monday, extras);
		extraWeek.setCnt(cnt);

		monday = monday.minusWeeks(1);
		sunday = sunday.minusWeeks(1);

		// 기타 횟수 통계 퍼센트 계산
		Long lastWeekCnt = extraRepository.findExtraCntByWeek(babyId, monday, sunday);
		Long cntPercent = getPercent(cnt, lastWeekCnt);
		Long lastWeekCntPercent = getPercent(lastWeekCnt, cnt);
		extraWeek.setCntPercent(cntPercent);
		extraWeek.setYesterdayCntPercent(lastWeekCntPercent);

		return extraWeek;
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

	/**
	 * Long 타입의 일주일 통계 계산하기
	 */
	public Long getStatisticsWeek(LocalDate day, Map<LocalDate, Long> data) {
		Long cnt = 0L;
		for (int i = 0; i < WEEK; i++) {
			cnt += data.getOrDefault(day, 0L);
			day = day.plusDays(1);
		}

		return cnt;
	}
}
