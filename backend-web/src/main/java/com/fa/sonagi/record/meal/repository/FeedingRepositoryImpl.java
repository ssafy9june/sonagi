package com.fa.sonagi.record.meal.repository;

import java.time.LocalDate;
import java.util.List;

import com.fa.sonagi.record.meal.dto.FeedingResDto;
import com.fa.sonagi.statistics.common.dto.Times;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.CaseBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

import static com.fa.sonagi.record.meal.entity.QFeeding.feeding;

@RequiredArgsConstructor
public class FeedingRepositoryImpl implements FeedingRepostioryCustom{

	private final JPAQueryFactory queryFactory;

	@Override
	public FeedingResDto findFeedingRecord(Long feedingId) {
		FeedingResDto feedings = queryFactory
			.select(Projections.bean(FeedingResDto.class,
				feeding.id.as("mealId"),
				feeding.leftStartTime,
				feeding.rightStartTime,
				feeding.leftEndTime,
				feeding.rightEndTime,
				feeding.createdTime,
				feeding.memo))
			.from(feeding)
			.where(feeding.id.eq(feedingId))
			.fetchOne();
		return feedings;
	}

	@Override
	public List<FeedingResDto> findByBabyIdAndCreatedDateOrderbyTime(Long babyId, LocalDate createdDate) {
		List<FeedingResDto> feedings = queryFactory
			.select(Projections.bean(FeedingResDto.class,
				feeding.id.as("mealId"),
				feeding.leftStartTime,
				feeding.rightStartTime,
				feeding.leftEndTime,
				feeding.rightEndTime,
				feeding.memo))
			.from(feeding)
			.where(feeding.babyId.eq(babyId), feeding.createdDate.eq(createdDate))
			.orderBy(new CaseBuilder().when(feeding.leftStartTime.before(feeding.rightStartTime))
				.then(feeding.leftStartTime)
				.otherwise(feeding.rightStartTime)
				.asc())
			.fetch();

		return feedings;
	}

	@Override
	public List<Times> findFeedingByDay(Long babyId, LocalDate createdDate) {
		List<Times> snackFeedingStatisticsQueryDto = queryFactory
			.select(Projections.bean(Times.class,
				feeding.leftStartTime.as("createdTime")))
			.from(feeding)
			.where(feeding.babyId.eq(babyId), feeding.createdDate.eq(createdDate))
			.fetch();

		return snackFeedingStatisticsQueryDto;
	}

	@Override
	public Long findFeedingCnt(Long babyId, LocalDate createdDate) {
		Long cnt = queryFactory
			.select(feeding.count())
			.from(feeding)
			.where(feeding.babyId.eq(babyId), feeding.createdDate.eq(createdDate))
			.fetchFirst();

		return cnt;
	}

	@Override
	public Long findFeedingCntByWeek(Long babyId, LocalDate monday, LocalDate sunday) {
		Long cnt = queryFactory
			.select(feeding.count())
			.from(feeding)
			.where(feeding.babyId.eq(babyId),
				feeding.createdDate.goe(monday), feeding.createdDate.loe(sunday))
			.fetchFirst();

		return cnt;
	}
}
