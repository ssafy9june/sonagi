package com.fa.sonagi.record.pumpingBreast.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fa.sonagi.record.pumpingBreast.dto.PumpingBreastResDto;
import com.fa.sonagi.statistics.pumpingBreast.dto.PumpingBreastStatisticsQueryDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

import static com.fa.sonagi.record.pumpingBreast.entity.QPumpingBreast.pumpingBreast;

@RequiredArgsConstructor
public class PumpingBreastRepositoryImpl implements PumpingBreastRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public PumpingBreastResDto findPumpingBreastRecord(Long pumpingBreastId) {
		PumpingBreastResDto pumpingBreasts = queryFactory
			.select(Projections.bean(PumpingBreastResDto.class,
				pumpingBreast.id.as("pumpingBreastId"),
				pumpingBreast.amount,
				pumpingBreast.memo,
				pumpingBreast.createdTime))
			.from(pumpingBreast)
			.where(pumpingBreast.id.eq(pumpingBreastId))
			.fetchOne();

		return pumpingBreasts;
	}

	@Override
	public List<PumpingBreastStatisticsQueryDto> findPumpingBreastByDay(Long babyId, LocalDate createdDate) {
		List<PumpingBreastStatisticsQueryDto> pumpingBreastStatisticsQueryDto = queryFactory
			.select(Projections.bean(PumpingBreastStatisticsQueryDto.class,
				pumpingBreast.createdTime,
				pumpingBreast.amount))
			.from(pumpingBreast)
			.where(pumpingBreast.babyId.eq(babyId), pumpingBreast.createdDate.eq(createdDate))
			.orderBy(pumpingBreast.createdTime.asc())
			.fetch();

		return pumpingBreastStatisticsQueryDto;
	}

	@Override
	public Long findPumpingBreastCnt(Long babyId, LocalDate createdDate) {
		Long cnt = queryFactory
			.select(pumpingBreast.count())
			.from(pumpingBreast)
			.where(pumpingBreast.babyId.eq(babyId), pumpingBreast.createdDate.eq(createdDate))
			.fetchFirst();

		return cnt;
	}

	@Override
	public Long findPumpingBreastAmount(Long babyId, LocalDate createdDate) {
		Long amount = queryFactory
			.select(pumpingBreast.amount.sum().coalesce(0L))
			.from(pumpingBreast)
			.where(pumpingBreast.babyId.eq(babyId), pumpingBreast.createdDate.eq(createdDate))
			.fetchFirst();

		return amount;
	}

	@Override
	public Map<LocalDate, Long> findPumpingBreastCnt(Long babyId, LocalDate monday, LocalDate sunday) {
			Map<LocalDate, Long> cnts = queryFactory
				.select(pumpingBreast.createdDate,
					pumpingBreast.count())
				.from(pumpingBreast)
				.where(pumpingBreast.babyId.eq(babyId),
					pumpingBreast.createdDate.goe(monday), pumpingBreast.createdDate.loe(sunday))
				.groupBy(pumpingBreast.createdDate)
				.stream()
				.collect(Collectors.toMap(
					tuple -> tuple.get(pumpingBreast.createdDate),
					tuple -> tuple.get(pumpingBreast.count())
				));

			return cnts;
	}

	@Override
	public Map<LocalDate, Long> findPumpingBreastAmount(Long babyId, LocalDate monday, LocalDate sunday) {
		Map<LocalDate, Long> amounts = queryFactory
			.select(pumpingBreast.createdDate,
				pumpingBreast.amount.sum().coalesce(0L))
			.from(pumpingBreast)
			.where(pumpingBreast.babyId.eq(babyId),
				pumpingBreast.createdDate.goe(monday), pumpingBreast.createdDate.loe(sunday))
			.groupBy(pumpingBreast.createdDate)
			.fetch()
			.stream()
			.collect(Collectors.toMap(
				tuple -> tuple.get(pumpingBreast.createdDate),
				tuple -> tuple.get(pumpingBreast.amount.sum().coalesce(0L))
			));

		return amounts;
	}

	@Override
	public Long findPumpingBreastCntByWeek(Long babyId, LocalDate monday, LocalDate sunday) {
		Long cnt = queryFactory
			.select(pumpingBreast.count())
			.from(pumpingBreast)
			.where(pumpingBreast.babyId.eq(babyId),
				pumpingBreast.createdDate.goe(monday), pumpingBreast.createdDate.loe(sunday))
			.fetchFirst();

		return cnt;
	}

	@Override
	public Long findPumpingBreastAmountByWeek(Long babyId, LocalDate monday, LocalDate sunday) {
		Long amount = queryFactory
			.select(pumpingBreast.amount.sum().coalesce(0L))
			.from(pumpingBreast)
			.where(pumpingBreast.babyId.eq(babyId),
				pumpingBreast.createdDate.goe(monday), pumpingBreast.createdDate.loe(sunday))
			.fetchFirst();

		return amount;
	}

}
