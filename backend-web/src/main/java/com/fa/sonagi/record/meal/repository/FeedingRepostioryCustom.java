package com.fa.sonagi.record.meal.repository;

import java.time.LocalDate;
import java.util.List;

import com.fa.sonagi.record.meal.dto.FeedingResDto;
import com.fa.sonagi.statistics.common.dto.Times;

public interface FeedingRepostioryCustom {
	FeedingResDto findFeedingRecord(Long feedingId);

	List<FeedingResDto> findByBabyIdAndCreatedDateOrderbyTime(Long babyId, LocalDate createdDate);

	List<Times> findFeedingByDay(Long babyId, LocalDate createdDate);

	Long findFeedingCnt(Long babyId, LocalDate createdDate);

	Long findFeedingCntByWeek(Long babyId, LocalDate monday, LocalDate sunday);
}
