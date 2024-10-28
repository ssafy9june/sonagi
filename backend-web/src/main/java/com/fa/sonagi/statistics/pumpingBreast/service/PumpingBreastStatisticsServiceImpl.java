package com.fa.sonagi.statistics.pumpingBreast.service;

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

import com.fa.sonagi.record.pumpingBreast.repository.PumpingBreastRepository;
import com.fa.sonagi.statistics.common.dto.Times;
import com.fa.sonagi.statistics.pumpingBreast.dto.PumpingBreastStatisticsDayForWeekDto;
import com.fa.sonagi.statistics.pumpingBreast.dto.PumpingBreastStatisticsResDto;
import com.fa.sonagi.statistics.pumpingBreast.dto.PumpingBreastStatisticsQueryDto;
import com.fa.sonagi.statistics.pumpingBreast.dto.PumpingBreastStatisticsWeekResDto;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PumpingBreastStatisticsServiceImpl implements PumpingBreastStatisticsService {

	private final PumpingBreastRepository pumpingBreastRepository;
	private final int WEEK = 7;

	/**
	 * 일별 통계 계산
	 */
	@Override
	public PumpingBreastStatisticsResDto getPumpingBreastStatisticsDay(Long babyId, LocalDate createdDate) {
		PumpingBreastStatisticsResDto pumpingBreastStatisticsResDto = new PumpingBreastStatisticsResDto();

		List<Times> pumpingBreastDay = new ArrayList<>();
		// 카테고리 데이터 조회
		List<PumpingBreastStatisticsQueryDto> pumpingBreasts = pumpingBreastRepository.findPumpingBreastByDay(babyId,
			createdDate);
		for (int i = 0; i < pumpingBreasts.size(); i++) {
			pumpingBreastDay.add(new Times(pumpingBreasts.get(i).getCreatedTime()));
		}
		pumpingBreastStatisticsResDto.setTimes(pumpingBreastDay);

		// 카테고리 카드 통계 조회
		Long cnt = (long)pumpingBreasts.size();
		pumpingBreastStatisticsResDto.setCnt(cnt);

		Long amount = 0L;
		for (int i = 0; i < pumpingBreasts.size(); i++) {
			amount += pumpingBreasts.get(i).getAmount();
		}
		pumpingBreastStatisticsResDto.setAmount(amount);

		// 하루 전 데이터 조회
		createdDate = createdDate.minus(1, ChronoUnit.DAYS);

		// 유축 횟수 통계 퍼센트 계산
		Long yesterdayCnt = pumpingBreastRepository.findPumpingBreastCnt(babyId, createdDate);
		Long cntPercent = getPercent(cnt, yesterdayCnt);
		Long yesterdayCntPercent = getPercent(yesterdayCnt, cnt);
		pumpingBreastStatisticsResDto.setCntPercent(cntPercent);
		pumpingBreastStatisticsResDto.setYesterdayCntPercent(yesterdayCntPercent);

		// 유축 용량 통계 퍼센트 계산
		Long yesterdayAmount = pumpingBreastRepository.findPumpingBreastAmount(babyId, createdDate);
		Long amountPercent = getPercent(amount, yesterdayAmount);
		Long yesterdayAmountPercent = getPercent(yesterdayAmount, amount);
		pumpingBreastStatisticsResDto.setAmountPercent(amountPercent);
		pumpingBreastStatisticsResDto.setYesterdayAmountPercent(yesterdayAmountPercent);

		return pumpingBreastStatisticsResDto;
	}

	/**
	 * 주별 통계 계산
	 */
	@Override
	public PumpingBreastStatisticsWeekResDto getPumpingBreastStatisticsWeek(Long babyId, LocalDate createdDate) {
		LocalDate monday = createdDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
		LocalDate sunday = createdDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
		PumpingBreastStatisticsWeekResDto pumpingBreastWeek = new PumpingBreastStatisticsWeekResDto();

		// 일주일 데이터 조회
		Map<LocalDate, Long> pumpingBreastCnts = pumpingBreastRepository.findPumpingBreastCnt(babyId, monday, sunday);
		Map<LocalDate, Long> pumpingBreastAmounts = pumpingBreastRepository.findPumpingBreastAmount(babyId, monday, sunday);

		// 날짜별 데이터 세팅
		LocalDate writeDay = monday;
		Long mostAmount = getMostAmount(pumpingBreastAmounts, writeDay);
		for (int i = 0; i < WEEK; i++) {
			PumpingBreastStatisticsDayForWeekDto pumpingBreastDay = new PumpingBreastStatisticsDayForWeekDto();

			if (pumpingBreastCnts.containsKey(writeDay))
				pumpingBreastDay.setCnt(pumpingBreastCnts.get(writeDay));
			else
				pumpingBreastDay.setCnt(0L);

			if (pumpingBreastAmounts.containsKey(writeDay)){
				Long amountPercent = getPercent(pumpingBreastAmounts.get(writeDay), mostAmount);
				pumpingBreastDay.setAmount(amountPercent);
			}
			else
				pumpingBreastDay.setAmount(0L);

			pumpingBreastWeek.getPumpingBreastStatistics().put(writeDay.format(DateTimeFormatter.ofPattern("M/dd")), pumpingBreastDay);
			writeDay = writeDay.plusDays(1);
		}

		// 카테고리별 일주일 통계 조회
		Long cnt = getStatisticsWeek(monday, pumpingBreastCnts);
		pumpingBreastWeek.setCnt(cnt);
		Long amount = getStatisticsWeek(monday, pumpingBreastAmounts);
		pumpingBreastWeek.setAmount(amount);

		monday = monday.minusWeeks(1);
		sunday = sunday.minusWeeks(1);

		// 유축 횟수 통계 퍼센트 계산
		Long lastWeekCnt = pumpingBreastRepository.findPumpingBreastCntByWeek(babyId, monday, sunday);
		Long cntPercent = getPercent(cnt, lastWeekCnt);
		Long lastWeekCntPercent = getPercent(lastWeekCnt, cnt);
		pumpingBreastWeek.setCntPercent(cntPercent);
		pumpingBreastWeek.setYesterdayCntPercent(lastWeekCntPercent);

		// 유축 용량 통계 퍼센트 계산
		Long lastWeekAmount = pumpingBreastRepository.findPumpingBreastAmountByWeek(babyId, monday, sunday);
		Long amountPercent = getPercent(amount, lastWeekAmount);
		Long lastWeekAmountPercent = getPercent(lastWeekAmount, amount);
		pumpingBreastWeek.setAmountPercent(amountPercent);
		pumpingBreastWeek.setYesterdayAmountPercent(lastWeekAmountPercent);

		return pumpingBreastWeek;
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

	/**
	 * 최대 용량 계산하기
	 */
	public Long getMostAmount(Map<LocalDate, Long> amounts, LocalDate writeDay) {
		Long mostAmount = 0L;
		for (int i = 0; i < WEEK; i++) {
			if (amounts.containsKey(writeDay)) {
				mostAmount = Math.max(mostAmount, amounts.get(writeDay));
			}
			writeDay = writeDay.plusDays(1);
		}

		return mostAmount;
	}
}
