package com.fa.sonagi.record.meal.repository;

import com.fa.sonagi.record.meal.dto.MealResDto;
import com.fa.sonagi.statistics.meal.dto.MealStatisticsQueryDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

import static com.fa.sonagi.record.meal.entity.QBabyFood.babyFood;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class BabyFoodRepositoryImpl implements BabyFoodRepositoryCustom{

	private final JPAQueryFactory queryFactory;

	@Override
	public MealResDto findBabyFoodRecord(Long babyFoodId) {
		MealResDto babyFoods = queryFactory
			.select(Projections.bean(MealResDto.class,
				babyFood.id.as("mealId"),
				babyFood.amount,
				babyFood.memo,
				babyFood.createdTime))
			.from(babyFood)
			.where(babyFood.id.eq(babyFoodId))
			.fetchOne();

		return babyFoods;
	}

	@Override
	public List<MealStatisticsQueryDto> findBabyFoodByDay(Long babyId, LocalDate createdDate) {
		List<MealStatisticsQueryDto> mealStatisticsQueryDto = queryFactory
			.select(Projections.bean(MealStatisticsQueryDto.class,
				babyFood.createdTime,
				babyFood.amount))
			.from(babyFood)
			.where(babyFood.babyId.eq(babyId), babyFood.createdDate.eq(createdDate))
			.fetch();

		return mealStatisticsQueryDto;
	}

	@Override
	public Long findBabyFoodCnt(Long babyId, LocalDate createdDate) {
		Long cnt = queryFactory
			.select(babyFood.count())
			.from(babyFood)
			.where(babyFood.babyId.eq(babyId), babyFood.createdDate.eq(createdDate))
			.fetchFirst();

		return cnt;
	}

	@Override
	public Long findBabyFoodAmount(Long babyId, LocalDate createdDate) {
		Long amount = queryFactory
			.select(babyFood.amount.sum().coalesce(0L))
			.from(babyFood)
			.where(babyFood.babyId.eq(babyId), babyFood.createdDate.eq(createdDate))
			.fetchFirst();

		return amount;
	}

	@Override
	public Map<LocalDate, Long> findBabyFoodAmount(Long babyId, LocalDate monday, LocalDate sunday) {
		Map<LocalDate, Long> amounts = queryFactory
			.select(babyFood.createdDate,
				babyFood.amount.sum().coalesce(0L))
			.from(babyFood)
			.where(babyFood.babyId.eq(babyId),
				babyFood.createdDate.goe(monday), babyFood.createdDate.loe(sunday))
			.groupBy(babyFood.createdDate)
			.fetch()
			.stream()
			.collect(Collectors.toMap(
				tuple -> tuple.get(babyFood.createdDate),
				tuple -> tuple.get(babyFood.amount.sum().coalesce(0L))
			));

		return amounts;
	}

	@Override
	public Long findBabyFoodCntByWeek(Long babyId, LocalDate monday, LocalDate sunday) {
		Long cnt = queryFactory
			.select(babyFood.count())
			.from(babyFood)
			.where(babyFood.babyId.eq(babyId),
				babyFood.createdDate.goe(monday), babyFood.createdDate.loe(sunday))
			.fetchFirst();

		return cnt;
	}

	@Override
	public Long findBabyFoodAmountByWeek(Long babyId, LocalDate monday, LocalDate sunday) {
		Long amount = queryFactory
			.select(babyFood.amount.sum().coalesce(0L))
			.from(babyFood)
			.where(babyFood.babyId.eq(babyId),
				babyFood.createdDate.goe(monday), babyFood.createdDate.loe(sunday))
			.fetchFirst();

		return amount;
	}
}
