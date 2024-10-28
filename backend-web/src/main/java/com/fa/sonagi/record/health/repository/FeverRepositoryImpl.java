package com.fa.sonagi.record.health.repository;

import static com.fa.sonagi.record.health.entity.QFever.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

import com.fa.sonagi.record.health.dto.FeverResDto;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.MathExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FeverRepositoryImpl implements FeverRepositoryCustom {
	private final JPAQueryFactory queryFactory;

	@Override
	public FeverResDto findFeverRecord(Long feverId) {
		FeverResDto fevers = queryFactory
			.select(Projections.bean(FeverResDto.class,
				fever.id.as("healthId"),
				fever.createdTime,
				fever.bodyTemperature,
				fever.memo))
			.from(fever)
			.where(fever.id.eq(feverId))
			.fetchOne();
		return fevers;
	}

	@Override
	public Double findFeverAvg(Long babyId, LocalDate createdDate) {
		Double bodyTemperature = queryFactory
			.select(MathExpressions.round(fever.bodyTemperature.avg(), 1).coalesce((double)0))
			.from(fever)
			.where(fever.babyId.eq(babyId), fever.createdDate.eq(createdDate))
			.fetchOne();

		return bodyTemperature;
	}

	@Override
	public Map<LocalDate, Double> findFeverAvg(Long babyId, LocalDate monday, LocalDate sunday) {
		Map<LocalDate, Double> bodyTemperatures = queryFactory
			.select(fever.createdDate,
				MathExpressions.round(fever.bodyTemperature.avg(), 1).coalesce((double)0))
			.from(fever)
			.where(fever.babyId.eq(babyId),
				fever.createdDate.goe(monday), fever.createdDate.loe(sunday))
			.groupBy(fever.createdDate)
			.fetch()
			.stream()
			.collect(Collectors.toMap(
				tuple -> tuple.get(fever.createdDate),
				tuple -> tuple.get(MathExpressions.round(fever.bodyTemperature.avg(), 1).coalesce((double)0))
			));

		return bodyTemperatures;
	}

	@Override
	public Double findFeverAvgByWeek(Long babyId, LocalDate monday, LocalDate sunday) {
		Double bodyTemperature = queryFactory
			.select(MathExpressions.round(fever.bodyTemperature.avg(), 1).coalesce((double)0))
			.from(fever)
			.where(fever.babyId.eq(babyId),
				fever.createdDate.goe(monday), fever.createdDate.loe(sunday))
			.fetchFirst();

		return bodyTemperature;
	}
}
