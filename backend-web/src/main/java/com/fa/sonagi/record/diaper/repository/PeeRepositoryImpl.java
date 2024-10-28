package com.fa.sonagi.record.diaper.repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.fa.sonagi.record.allCategory.dto.StatisticsTime;
import com.fa.sonagi.record.diaper.dto.DiaperResDto;
import com.fa.sonagi.statistics.common.dto.Times;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

import static com.fa.sonagi.record.diaper.entity.QPee.pee;

@RequiredArgsConstructor
public class PeeRepositoryImpl implements PeeRepositoryCustom{

	private final JPAQueryFactory queryFactory;


	@Override
	public DiaperResDto findPeeRecord(Long peeId) {
		DiaperResDto pees = queryFactory
			.select(Projections.bean(DiaperResDto.class,
				pee.id.as("diaperId"),
				pee.createdTime,
				pee.memo))
			.from(pee)
			.where(pee.id.eq(peeId))
			.fetchOne();
		return pees;
	}

	@Override
	public List<Times> findPeeByDay(Long babyId, LocalDate createdDate) {
		List<Times> pees = queryFactory
			.select(Projections.bean(Times.class,
				pee.createdTime))
			.from(pee)
			.where(pee.babyId.eq(babyId), pee.createdDate.eq(createdDate))
			.fetch();

		return pees;
	}

	@Override
	public Long findPeeCnt(Long babyId, LocalDate createdDate) {
		Long cnt = queryFactory
			.select(pee.count())
			.from(pee)
			.where(pee.babyId.eq(babyId), pee.createdDate.eq(createdDate))
			.fetchFirst();

		return cnt;
	}

	@Override
	public Map<LocalDate, List<java.time.LocalTime>> findPeeForWeek(Long babyId, LocalDate monday, LocalDate sunday) {
		List<StatisticsTime> pees = queryFactory
			.select(Projections.bean(StatisticsTime.class,
				pee.createdDate,
				pee.createdTime))
			.from(pee)
			.where(pee.babyId.eq(babyId),
				pee.createdDate.goe(monday), pee.createdDate.loe(sunday))
			.fetch();

		return pees.stream()
			.collect(Collectors.groupingBy(p -> p.getCreatedDate(),
				Collectors.mapping(StatisticsTime::getCreatedTime, Collectors.toList())));
	}

	@Override
	public Long findPeeCntByWeek(Long babyId, LocalDate monday, LocalDate sunday) {
		Long cnt = queryFactory
			.select(pee.count())
			.from(pee)
			.where(pee.babyId.eq(babyId),
				pee.createdDate.goe(monday), pee.createdDate.loe(sunday))
			.fetchFirst();

		return cnt;
	}
}
