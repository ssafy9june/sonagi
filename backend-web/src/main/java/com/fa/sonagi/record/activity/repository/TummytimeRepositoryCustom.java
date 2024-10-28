package com.fa.sonagi.record.activity.repository;

import java.time.LocalDate;
import java.util.List;

import com.fa.sonagi.record.activity.dto.ActivityResDto;
import com.fa.sonagi.record.allCategory.dto.StatisticsTime;
import com.fa.sonagi.statistics.common.dto.EndTimes;

public interface TummytimeRepositoryCustom {
	ActivityResDto findTummytimeRecord(Long tummytimeId);

	List<EndTimes> findTummytimeByDay(Long babyId, LocalDate createdDate);

	List<StatisticsTime> findTummytimeForWeek(Long babyId, LocalDate startDay, LocalDate endDay);
}
