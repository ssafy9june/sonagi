package com.fa.sonagi.statistics.meal.service;

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

import com.fa.sonagi.record.meal.repository.BabyFoodRepository;
import com.fa.sonagi.record.meal.repository.BreastFeedingRepository;
import com.fa.sonagi.record.meal.repository.FeedingRepository;
import com.fa.sonagi.record.meal.repository.InfantFormulaRepository;
import com.fa.sonagi.record.meal.repository.MilkRepository;
import com.fa.sonagi.record.meal.repository.SnackRepository;
import com.fa.sonagi.statistics.common.dto.Times;
import com.fa.sonagi.statistics.meal.dto.MealStatisticsDayForWeekDto;
import com.fa.sonagi.statistics.meal.dto.MealStatisticsQueryDto;
import com.fa.sonagi.statistics.meal.dto.MealStatisticsResDto;
import com.fa.sonagi.statistics.meal.dto.MealStatisticsWeekResDto;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MealStatisticsServiceImpl implements MealStatisticsService{

	private final BabyFoodRepository babyFoodRepository;
	private final BreastFeedingRepository breastFeedingRepository;
	private final FeedingRepository feedingRepository;
	private final InfantFormulaRepository infantFormulaRepository;
	private final MilkRepository milkRepository;
	private final SnackRepository snackRepository;
	private final int WEEK = 7;

	/**
	 * 일별 통계 계산
	 */
	@Override
	public MealStatisticsResDto getMealStatisticsDay(Long babyId, LocalDate createdDate) {
		MealStatisticsResDto mealStatisticsResDto = new MealStatisticsResDto();

		List<Times> mealDay = new ArrayList<>();
		// 데이터 조회
		List<MealStatisticsQueryDto> babyFoods = babyFoodRepository.findBabyFoodByDay(babyId, createdDate);
		for (int i = 0; i < babyFoods.size(); i++) {
			mealDay.add(new Times(babyFoods.get(i).getCreatedTime()));
		}
		List<MealStatisticsQueryDto> breastFeedings = breastFeedingRepository.findBreastFeedingByDay(babyId,
			createdDate);
		for (int i = 0; i < breastFeedings.size(); i++) {
			mealDay.add(new Times(breastFeedings.get(i).getCreatedTime()));
		}
		List<MealStatisticsQueryDto> infantFormulas = infantFormulaRepository.findInfantFormulaByDay(babyId,
			createdDate);
		for (int i = 0; i < infantFormulas.size(); i++) {
			mealDay.add(new Times(infantFormulas.get(i).getCreatedTime()));
		}
		List<MealStatisticsQueryDto> milks = milkRepository.findMilkByDay(babyId, createdDate);
		for (int i = 0; i < milks.size(); i++) {
			mealDay.add(new Times(milks.get(i).getCreatedTime()));
		}
		List<Times> feedings = feedingRepository.findFeedingByDay(babyId, createdDate);
		for (Times t : feedings)
			mealDay.add(t);
		List<Times> snacks = snackRepository.findSnackByDay(babyId, createdDate);
		for (Times t : snacks)
			mealDay.add(t);
		Collections.sort(mealDay, Comparator.comparing(Times::getCreatedTime));
		mealStatisticsResDto.setTimes(mealDay);

		// 횟수 통계
		Long cnt = (long)(babyFoods.size() + breastFeedings.size() + feedings.size() + infantFormulas.size()
			+ milks.size() + snacks.size());
		mealStatisticsResDto.setCnt(cnt);

		// 용량 통계
		Long amount = 0L;
		amount += sumAmount(babyFoods);
		amount += sumAmount(breastFeedings);
		amount += sumAmount(infantFormulas);
		amount += sumAmount(milks);
		mealStatisticsResDto.setAmount(amount);

		createdDate = createdDate.minus(1, ChronoUnit.DAYS);

		Long yesterdayCnt = babyFoodRepository.findBabyFoodCnt(babyId, createdDate)
			+ breastFeedingRepository.findBreastFeedingCnt(babyId, createdDate)
			+ feedingRepository.findFeedingCnt(babyId, createdDate)
			+ infantFormulaRepository.findInfantFormulaCnt(babyId, createdDate)
			+ milkRepository.findMilkCnt(babyId, createdDate)
			+ snackRepository.findSnackCnt(babyId, createdDate);

		Long cntPercent = getPercent(cnt, yesterdayCnt);
		Long yesterdayCntPercent = getPercent(yesterdayCnt, cnt);
		mealStatisticsResDto.setCntPercent(cntPercent);
		mealStatisticsResDto.setYesterdayCntPercent(yesterdayCntPercent);

		Long yesterdayAmount = babyFoodRepository.findBabyFoodAmount(babyId, createdDate)
			+ breastFeedingRepository.findBreastFeedingAmount(babyId, createdDate)
			+ infantFormulaRepository.findInfantFormulaAmount(babyId, createdDate)
			+ milkRepository.findMilkAmount(babyId, createdDate);

		Long amountPercent = getPercent(amount, yesterdayAmount);
		Long yesterdayAmountPercent = getPercent(yesterdayAmount, amount);
		mealStatisticsResDto.setAmountPercent(amountPercent);
		mealStatisticsResDto.setYesterdayAmountPercent(yesterdayAmountPercent);

		return mealStatisticsResDto;
	}

	@Override
	public MealStatisticsWeekResDto getMealStatisticsWeek(Long babyId, LocalDate createdDate) {
		LocalDate monday = createdDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
		LocalDate sunday = createdDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
		MealStatisticsWeekResDto mealWeek = new MealStatisticsWeekResDto();

		// 일주일 데이터 조회
		Map<LocalDate, Long> babyFoodAmounts = babyFoodRepository.findBabyFoodAmount(babyId, monday, sunday);
		Map<LocalDate, Long> breastFeedingAmounts = breastFeedingRepository.findBreastFeedingAmount(babyId, monday,
			sunday);
		Map<LocalDate, Long> infantFormulaAmounts = infantFormulaRepository.findInfantFormulaAmount(babyId, monday,
			sunday);
		Map<LocalDate, Long> milkAmounts = milkRepository.findMilkAmount(babyId, monday, sunday);

		// 날짜별 데이터 세팅
		LocalDate writeDay = monday;
		Long[][] amountWEEK = getPercentByDay(babyFoodAmounts, breastFeedingAmounts, infantFormulaAmounts, milkAmounts,
			writeDay);
		for (int i = 0; i < WEEK; i++) {
			MealStatisticsDayForWeekDto mealDay = new MealStatisticsDayForWeekDto();

			if (babyFoodAmounts.containsKey(writeDay))
				mealDay.setBabyFoodAmount(
					Math.floor(getPercent(babyFoodAmounts.get(writeDay), amountWEEK[i][0], amountWEEK[i][1]) * 100) / 100);
			else
				mealDay.setBabyFoodAmount((double)0);
			if (breastFeedingAmounts.containsKey(writeDay))
				mealDay.setBreastFeedingAmount(
					Math.floor(getPercent(breastFeedingAmounts.get(writeDay), amountWEEK[i][0], amountWEEK[i][1]) * 100) / 100);
			else
				mealDay.setBreastFeedingAmount((double)0);
			if (infantFormulaAmounts.containsKey(writeDay))
				mealDay.setInfantFormulaAmount(
					Math.floor(getPercent(infantFormulaAmounts.get(writeDay), amountWEEK[i][0], amountWEEK[i][1]) * 100) / 100);
			else
				mealDay.setInfantFormulaAmount((double)0);
			if (milkAmounts.containsKey(writeDay))
				mealDay.setMilkAmount(Math.floor(getPercent(milkAmounts.get(writeDay), amountWEEK[i][0], amountWEEK[i][1]) * 100) / 100);
			else
				mealDay.setMilkAmount((double)0);

			mealWeek.getMealStatistics().put(writeDay.format(DateTimeFormatter.ofPattern("M/dd")), mealDay);
			writeDay = writeDay.plusDays(1);
		}

		// 카테고리별 일주일 통계 조회
		Long cnt = babyFoodRepository.findBabyFoodCntByWeek(babyId, monday, sunday)
			+ breastFeedingRepository.findBreastFeedingCntByWeek(babyId, monday, sunday)
			+ feedingRepository.findFeedingCntByWeek(babyId, monday, sunday)
			+ infantFormulaRepository.findInfantFormulaCntByWeek(babyId, monday, sunday)
			+ milkRepository.findMilkCntByWeek(babyId, monday, sunday)
			+ snackRepository.findSnackCntByWeek(babyId, monday, sunday);
		mealWeek.setCnt(cnt);

		Long amount = 0L;
		for (int i = 0; i < WEEK; i++) {
			amount += amountWEEK[i][0];
		}
		mealWeek.setAmount(amount);

		monday = monday.minusWeeks(1);
		sunday = sunday.minusWeeks(1);

		// 식사 횟수 통계 퍼센트 계산
		Long lastWeekCnt = babyFoodRepository.findBabyFoodCntByWeek(babyId, monday, sunday)
			+ breastFeedingRepository.findBreastFeedingCntByWeek(babyId, monday, sunday)
			+ feedingRepository.findFeedingCntByWeek(babyId, monday, sunday)
			+ infantFormulaRepository.findInfantFormulaCntByWeek(babyId, monday, sunday)
			+ milkRepository.findMilkCntByWeek(babyId, monday, sunday)
			+ snackRepository.findSnackCntByWeek(babyId, monday, sunday);

		Long cntPercent = getPercent(cnt, lastWeekCnt);
		Long lastWeekCntPercent = getPercent(lastWeekCnt, cnt);
		mealWeek.setCntPercent(cntPercent);
		mealWeek.setYesterdayCntPercent(lastWeekCntPercent);

		// 식사 용량 통계 퍼센트 계산
		Long lastWeekAmount = babyFoodRepository.findBabyFoodAmountByWeek(babyId, monday, sunday)
			+ breastFeedingRepository.findBreastFeedingAmountByWeek(babyId, monday, sunday)
			+ infantFormulaRepository.findInfantFormulaAmountByWeek(babyId, monday, sunday)
			+ milkRepository.findMilkAmountByWeek(babyId, monday, sunday);

		Long amountPercent = getPercent(amount, lastWeekAmount);
		Long lastWeekAmountPercent = getPercent(lastWeekAmount, amount);
		mealWeek.setAmountPercent(amountPercent);
		mealWeek.setYesterdayAmountPercent(lastWeekAmountPercent);

		return mealWeek;
	}

	/**
	 * 퍼센트 계산하기 Day
	 */
	public Long getPercent(Long target, Long opponent) {
		if (target == 0)
			return 0L;
		else if (target >= opponent)
			return 100L;
		else
			return target * 100 / opponent;
	}

	/**
	 * 퍼센트 계산하기 Week
	 */
	public Double getPercent(Long target, Long opponent, Long percent) {
		if (opponent == 0)
			return (double)0L;
		else
			return (double)target * (double)percent / (double)opponent;
	}

	/**
	 * 총 용량 계산하기
	 */
	public Long sumAmount(List<MealStatisticsQueryDto> amounts) {
		Long amount = 0L;
		for (int i = 0; i < amounts.size(); i++) {
			amount += amounts.get(i).getAmount();
		}

		return amount;
	}

	/**
	 * 날짜별 퍼센트 구하기
	 */
	public Long[][] getPercentByDay(Map<LocalDate, Long> babyFood, Map<LocalDate, Long> breastFeeding,
		Map<LocalDate, Long> infantFormula, Map<LocalDate, Long> milk, LocalDate writeDay) {
		Long[][] amountWeek = new Long[WEEK][2];
		long mostAmount = 0L;

		// 최대 용량 계산하기
		for (int i = 0; i < WEEK; i++) {
			Long amount = 0L;
			if (babyFood.containsKey(writeDay))
				amount += babyFood.get(writeDay);
			if (breastFeeding.containsKey(writeDay))
				amount += breastFeeding.get(writeDay);
			if (infantFormula.containsKey(writeDay))
				amount += infantFormula.get(writeDay);
			if (milk.containsKey(writeDay))
				amount += milk.get(writeDay);

			amountWeek[i][0] = amount;
			mostAmount = Math.max(mostAmount, amount);
			writeDay = writeDay.plusDays(1);
		}

		// 날짜별 percent 구하기
		for (int i = 0; i < WEEK; i++) {
			amountWeek[i][1] = getPercent(amountWeek[i][0], mostAmount);
		}

		return amountWeek;
	}
}
