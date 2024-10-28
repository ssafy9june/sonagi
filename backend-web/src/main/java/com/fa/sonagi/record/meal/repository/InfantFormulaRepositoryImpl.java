package com.fa.sonagi.record.meal.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fa.sonagi.record.meal.dto.MealResDto;
import com.fa.sonagi.statistics.meal.dto.MealStatisticsQueryDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

import static com.fa.sonagi.record.meal.entity.QInfantFormula.infantFormula;

@RequiredArgsConstructor
public class InfantFormulaRepositoryImpl implements InfantFormulaRepositoryCustom{

	private final JPAQueryFactory queryFactory;

	@Override
	public MealResDto findInfantFormulaRecord(Long infantFormulaId) {
		MealResDto infantFormulas = queryFactory
			.select(Projections.bean(MealResDto.class,
				infantFormula.id.as("mealId"),
				infantFormula.amount,
				infantFormula.memo,
				infantFormula.createdTime))
			.from(infantFormula)
			.where(infantFormula.id.eq(infantFormulaId))
			.fetchOne();

		return infantFormulas;
	}

	@Override
	public List<MealStatisticsQueryDto> findInfantFormulaByDay(Long babyId, LocalDate createdDate) {
		List<MealStatisticsQueryDto> mealStatisticsQueryDto = queryFactory
			.select(Projections.bean(MealStatisticsQueryDto.class,
				infantFormula.createdTime,
				infantFormula.amount))
			.from(infantFormula)
			.where(infantFormula.babyId.eq(babyId), infantFormula.createdDate.eq(createdDate))
			.fetch();

		return mealStatisticsQueryDto;
	}

	@Override
	public Long findInfantFormulaCnt(Long babyId, LocalDate createdDate) {
		Long cnt = queryFactory
			.select(infantFormula.count())
			.from(infantFormula)
			.where(infantFormula.babyId.eq(babyId), infantFormula.createdDate.eq(createdDate))
			.fetchFirst();

		return cnt;
	}

	@Override
	public Long findInfantFormulaAmount(Long babyId, LocalDate createdDate) {
		Long amount = queryFactory
			.select(infantFormula.amount.sum().coalesce(0L))
			.from(infantFormula)
			.where(infantFormula.babyId.eq(babyId), infantFormula.createdDate.eq(createdDate))
			.fetchFirst();

		return amount;
	}

	@Override
	public Map<LocalDate, Long> findInfantFormulaAmount(Long babyId, LocalDate monday, LocalDate sunday) {
		Map<LocalDate, Long> amounts = queryFactory
			.select(infantFormula.createdDate,
				infantFormula.amount.sum().coalesce(0L))
			.from(infantFormula)
			.where(infantFormula.babyId.eq(babyId),
				infantFormula.createdDate.goe(monday), infantFormula.createdDate.loe(sunday))
			.groupBy(infantFormula.createdDate)
			.fetch()
			.stream()
			.collect(Collectors.toMap(
				tuple -> tuple.get(infantFormula.createdDate),
				tuple -> tuple.get(infantFormula.amount.sum().coalesce(0L))
			));

		return amounts;
	}

	@Override
	public Long findInfantFormulaCntByWeek(Long babyId, LocalDate monday, LocalDate sunday) {
		Long cnt = queryFactory
			.select(infantFormula.count())
			.from(infantFormula)
			.where(infantFormula.babyId.eq(babyId),
				infantFormula.createdDate.goe(monday), infantFormula.createdDate.loe(sunday))
			.fetchFirst();

		return cnt;
	}

	@Override
	public Long findInfantFormulaAmountByWeek(Long babyId, LocalDate monday, LocalDate sunday) {
		Long amount = queryFactory
			.select(infantFormula.amount.sum().coalesce(0L))
			.from(infantFormula)
			.where(infantFormula.babyId.eq(babyId),
				infantFormula.createdDate.goe(monday), infantFormula.createdDate.loe(sunday))
			.fetchFirst();

		return amount;
	}
}
