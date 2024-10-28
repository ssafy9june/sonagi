package com.fa.sonagi.record.meal.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fa.sonagi.record.meal.dto.MealResDto;
import com.fa.sonagi.statistics.meal.dto.MealStatisticsQueryDto;
import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.fa.sonagi.record.meal.entity.QBreastFeeding.breastFeeding;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BreastFeedingRepositoryImpl implements BreastFeedingRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public MealResDto findBreastFeedingRecord(Long breastFeedingId) {
		MealResDto breastFeedings = queryFactory
			.select(Projections.bean(MealResDto.class,
				breastFeeding.id.as("mealId"),
				breastFeeding.amount,
				breastFeeding.memo,
				breastFeeding.createdTime))
			.from(breastFeeding)
			.where(breastFeeding.id.eq(breastFeedingId))
			.fetchOne();

		return breastFeedings;
	}

	@Override
	public List<MealStatisticsQueryDto> findBreastFeedingByDay(Long babyId, LocalDate createdDate) {
		List<MealStatisticsQueryDto> mealStatisticsQueryDto = queryFactory
			.select(Projections.bean(MealStatisticsQueryDto.class,
				breastFeeding.createdTime,
				breastFeeding.amount))
			.from(breastFeeding)
			.where(breastFeeding.babyId.eq(babyId), breastFeeding.createdDate.eq(createdDate))
			.fetch();

		return mealStatisticsQueryDto;
	}

	@Override
	public Long findBreastFeedingCnt(Long babyId, LocalDate createdDate) {
		Long cnt = queryFactory
			.select(breastFeeding.count())
			.from(breastFeeding)
			.where(breastFeeding.babyId.eq(babyId), breastFeeding.createdDate.eq(createdDate))
			.fetchFirst();

		return cnt;
	}

	@Override
	public Long findBreastFeedingAmount(Long babyId, LocalDate createdDate) {
		Long amount = queryFactory
			.select(breastFeeding.amount.sum().coalesce(0L))
			.from(breastFeeding)
			.where(breastFeeding.babyId.eq(babyId), breastFeeding.createdDate.eq(createdDate))
			.fetchFirst();

		return amount;
	}

	@Override
	public Map<LocalDate, Long> findBreastFeedingAmount(Long babyId, LocalDate monday, LocalDate sunday) {
		Map<LocalDate, Long> amounts = queryFactory
			.select(breastFeeding.createdDate,
				breastFeeding.amount.sum().coalesce(0L))
			.from(breastFeeding)
			.where(breastFeeding.babyId.eq(babyId),
				breastFeeding.createdDate.goe(monday), breastFeeding.createdDate.loe(sunday))
			.groupBy(breastFeeding.createdDate)
			.fetch()
			.stream()
			.collect(Collectors.toMap(
				tuple -> tuple.get(breastFeeding.createdDate),
				tuple -> tuple.get(breastFeeding.amount.sum().coalesce(0L))
			));

		return amounts;
	}

	@Override
	public Long findBreastFeedingCntByWeek(Long babyId, LocalDate monday, LocalDate sunday) {
		Long cnt = queryFactory
			.select(breastFeeding.count())
			.from(breastFeeding)
			.where(breastFeeding.babyId.eq(babyId),
				breastFeeding.createdDate.goe(monday), breastFeeding.createdDate.loe(sunday))
			.fetchFirst();

		return cnt;
	}

	@Override
	public Long findBreastFeedingAmountByWeek(Long babyId, LocalDate monday, LocalDate sunday) {
		Long amount = queryFactory
			.select(breastFeeding.amount.sum().coalesce(0L))
			.from(breastFeeding)
			.where(breastFeeding.babyId.eq(babyId),
				breastFeeding.createdDate.goe(monday), breastFeeding.createdDate.loe(sunday))
			.fetchFirst();

		return amount;
	}
}
