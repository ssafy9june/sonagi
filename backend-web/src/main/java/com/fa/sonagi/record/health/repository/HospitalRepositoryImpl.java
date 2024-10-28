package com.fa.sonagi.record.health.repository;

import static com.fa.sonagi.record.health.entity.QHospital.*;

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
public class HospitalRepositoryImpl implements HospitalRepositoryCustom {
	private final JPAQueryFactory queryFactory;

	@Override
	public HealthResDto findHospitalRecord(Long hospitalId) {
		HealthResDto hospitals = queryFactory
			.select(Projections.bean(HealthResDto.class,
				hospital.id.as("healthId"),
				hospital.createdTime,
				hospital.memo))
			.from(hospital)
			.where(hospital.id.eq(hospitalId))
			.fetchOne();
		return hospitals;
	}

	@Override
	public List<Times> findHospitalByDay(Long babyId, LocalDate createdDate) {
		List<Times> healthStatisticsQueryDto = queryFactory
			.select(Projections.bean(Times.class,
				hospital.createdTime))
			.from(hospital)
			.where(hospital.babyId.eq(babyId), hospital.createdDate.eq(createdDate))
			.fetch();

		return healthStatisticsQueryDto;
	}

	@Override
	public Long findHospitalCnt(Long babyId, LocalDate createdDate) {
		Long cnt = queryFactory
			.select(hospital.count())
			.from(hospital)
			.where(hospital.babyId.eq(babyId), hospital.createdDate.eq(createdDate))
			.fetchFirst();

		return cnt;
	}

	@Override
	public Map<LocalDate, Long> findHospitalCnt(Long babyId, LocalDate monday, LocalDate sunday) {
		Map<LocalDate, Long> cnts = queryFactory
			.select(hospital.createdDate,
				hospital.count())
			.from(hospital)
			.where(hospital.babyId.eq(babyId),
				hospital.createdDate.goe(monday), hospital.createdDate.loe(sunday))
			.groupBy(hospital.createdDate)
			.fetch()
			.stream()
			.collect(Collectors.toMap(
				tuple -> tuple.get(hospital.createdDate),
				tuple -> tuple.get(hospital.count())
			));

		return cnts;
	}

	@Override
	public Long findHospitalCntByWeek(Long babyId, LocalDate monday, LocalDate sunday) {
		Long cnt = queryFactory
			.select(hospital.count())
			.from(hospital)
			.where(hospital.babyId.eq(babyId),
				hospital.createdDate.goe(monday), hospital.createdDate.loe(sunday))
			.fetchFirst();

		return cnt;
	}
}
