package com.fa.sonagi.record.health.repository;

import static com.fa.sonagi.record.health.entity.QMedication.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fa.sonagi.record.health.dto.HealthResDto;
import com.fa.sonagi.statistics.common.dto.Times;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MedicationRepositoryImpl implements MedicationRepositoryCustom {
	private final JPAQueryFactory queryFactory;

	@Override
	public HealthResDto findMedicationRecord(Long medicationId) {
		HealthResDto medications = queryFactory
			.select(Projections.bean(HealthResDto.class,
				medication.id.as("healthId"),
				medication.createdTime,
				medication.memo))
			.from(medication)
			.where(medication.id.eq(medicationId))
			.fetchOne();
		return medications;
	}

	@Override
	public List<Times> findMedicationByDay(Long babyId, LocalDate createdDate) {
		List<Times> healthStatisticsQueryDto = queryFactory
			.select(Projections.bean(Times.class,
				medication.createdTime))
			.from(medication)
			.where(medication.babyId.eq(babyId), medication.createdDate.eq(createdDate))
			.fetch();

		return healthStatisticsQueryDto;
	}

	@Override
	public Long findMedicationCnt(Long babyId, LocalDate createdDate) {
		Long cnt = queryFactory
			.select(medication.count())
			.from(medication)
			.where(medication.babyId.eq(babyId), medication.createdDate.eq(createdDate))
			.fetchFirst();

		return cnt;
	}

	@Override
	public Map<LocalDate, Long> findMedicationCnt(Long babyId, LocalDate monday, LocalDate sunday) {
		Map<LocalDate, Long> cnts = queryFactory
			.select(medication.createdDate,
				medication.count())
			.from(medication)
			.where(medication.babyId.eq(babyId),
				medication.createdDate.goe(monday), medication.createdDate.loe(sunday))
			.groupBy(medication.createdDate)
			.fetch()
			.stream()
			.collect(Collectors.toMap(
				tuple -> tuple.get(medication.createdDate),
				tuple -> tuple.get(medication.count())
			));

		return cnts;
	}

	@Override
	public Long findMedicationCntByWeek(Long babyId, LocalDate monday, LocalDate sunday) {
		Long cnt = queryFactory
			.select(medication.count())
			.from(medication)
			.where(medication.babyId.eq(babyId),
				medication.createdDate.goe(monday), medication.createdDate.loe(sunday))
			.fetchFirst();

		return cnt;
	}
}
