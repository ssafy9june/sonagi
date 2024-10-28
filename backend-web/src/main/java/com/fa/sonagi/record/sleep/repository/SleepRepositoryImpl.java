package com.fa.sonagi.record.sleep.repository;

import static com.fa.sonagi.record.sleep.entity.QSleep.*;

import java.time.LocalDate;
import java.util.List;

import com.fa.sonagi.record.allCategory.dto.StatisticsTime;
import com.fa.sonagi.record.sleep.dto.SleepResDto;
import com.fa.sonagi.statistics.common.dto.EndTimes;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class SleepRepositoryImpl implements SleepRepositoryCustom {
	private final JPAQueryFactory queryFactory;

	@Override
	public SleepResDto findSleepRecord(Long sleepId) {
		SleepResDto sleeps = queryFactory
			.select(Projections.bean(SleepResDto.class,
				sleep.id.as("sleepId"),
				sleep.createdTime,
				sleep.endTime,
				sleep.memo))
			.from(sleep)
			.where(sleep.id.eq(sleepId))
			.fetchOne();

		return sleeps;
	}

	@Override
	public List<EndTimes> findSleepByDay(Long babyId, LocalDate createdDate) {
		List<EndTimes> sleepStatisticsQueryDto = queryFactory
			.select(Projections.bean(EndTimes.class,
				sleep.createdTime,
				sleep.endTime))
			.from(sleep)
			.where(sleep.babyId.eq(babyId), sleep.createdDate.eq(createdDate))
			.fetch();

		return sleepStatisticsQueryDto;
	}

	@Override
	public List<StatisticsTime> findSleepForWeek(Long babyId, LocalDate startDay, LocalDate endDay) {
		List<StatisticsTime> sleeps = queryFactory
			.select(Projections.bean(StatisticsTime.class,
				sleep.createdDate,
				sleep.createdTime,
				sleep.endTime))
			.from(sleep)
			.where(sleep.babyId.eq(babyId),
				sleep.createdDate.goe(startDay), sleep.createdDate.loe(endDay))
			.fetch();

		return sleeps;
	}
}
