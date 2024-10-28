package com.fa.sonagi.record.activity.repository;

import static com.fa.sonagi.record.activity.entity.QTummytime.*;

import java.time.LocalDate;
import java.util.List;

import com.fa.sonagi.record.activity.dto.ActivityResDto;
import com.fa.sonagi.record.allCategory.dto.StatisticsTime;
import com.fa.sonagi.statistics.common.dto.EndTimes;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TummytimeRepositoryImpl implements TummytimeRepositoryCustom {
	private final JPAQueryFactory queryFactory;

	@Override
	public ActivityResDto findTummytimeRecord(Long tummytimeId) {
		ActivityResDto tummytimes = queryFactory
			.select(Projections.bean(ActivityResDto.class,
				tummytime.id.as("activityId"),
				tummytime.createdTime,
				tummytime.endTime,
				tummytime.memo))
			.from(tummytime)
			.where(tummytime.id.eq(tummytimeId))
			.fetchOne();

		return tummytimes;
	}

	@Override
	public List<EndTimes> findTummytimeByDay(Long babyId, LocalDate createdDate) {
		List<EndTimes> tummytimes = queryFactory
			.select(Projections.bean(EndTimes.class,
				tummytime.createdTime,
				tummytime.endTime))
			.from(tummytime)
			.where(tummytime.babyId.eq(babyId), tummytime.createdDate.eq(createdDate))
			.fetch();

		return tummytimes;
	}

	@Override
	public List<StatisticsTime> findTummytimeForWeek(Long babyId, LocalDate startDay, LocalDate endDay) {
		List<StatisticsTime> tummytimes = queryFactory
			.select(Projections.bean(StatisticsTime.class,
				tummytime.createdDate,
				tummytime.createdTime,
				tummytime.endTime))
			.from(tummytime)
			.where(tummytime.babyId.eq(babyId),
				tummytime.createdDate.goe(startDay), tummytime.createdDate.loe(endDay))
			.fetch();

		return tummytimes;
	}
}
