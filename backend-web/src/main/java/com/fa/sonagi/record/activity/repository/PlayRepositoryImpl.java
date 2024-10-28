package com.fa.sonagi.record.activity.repository;

import static com.fa.sonagi.record.activity.entity.QPlay.*;

import java.time.LocalDate;
import java.util.List;

import com.fa.sonagi.record.activity.dto.ActivityResDto;
import com.fa.sonagi.record.allCategory.dto.StatisticsTime;
import com.fa.sonagi.statistics.common.dto.EndTimes;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlayRepositoryImpl implements PlayRepositoryCustom {
	private final JPAQueryFactory queryFactory;

	@Override
	public ActivityResDto findPlayRecord(Long playId) {
		ActivityResDto plays = queryFactory
			.select(Projections.bean(ActivityResDto.class,
				play.id.as("activityId"),
				play.createdTime,
				play.endTime,
				play.memo))
			.from(play)
			.where(play.id.eq(playId))
			.fetchOne();
		return plays;
	}

	@Override
	public List<EndTimes> findPlayByDay(Long babyId, LocalDate createdDate) {
		List<EndTimes> plays = queryFactory
			.select(Projections.bean(EndTimes.class,
				play.createdTime,
				play.endTime))
			.from(play)
			.where(play.babyId.eq(babyId), play.createdDate.eq(createdDate))
			.fetch();

		return plays;
	}

	@Override
	public List<StatisticsTime> findPlayForWeek(Long babyId, LocalDate startDay, LocalDate endDay) {
		List<StatisticsTime> plays = queryFactory
			.select(Projections.bean(StatisticsTime.class,
				play.createdDate,
				play.createdTime,
				play.endTime))
			.from(play)
			.where(play.babyId.eq(babyId),
				play.createdDate.goe(startDay), play.createdDate.loe(endDay))
			.fetch();

		return plays;
	}
}
