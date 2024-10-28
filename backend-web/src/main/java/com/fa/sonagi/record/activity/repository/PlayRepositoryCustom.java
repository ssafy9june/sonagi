package com.fa.sonagi.record.activity.repository;

import java.time.LocalDate;
import java.util.List;

import com.fa.sonagi.record.activity.dto.ActivityResDto;
import com.fa.sonagi.record.allCategory.dto.StatisticsTime;
import com.fa.sonagi.statistics.common.dto.EndTimes;

public interface PlayRepositoryCustom {
	ActivityResDto findPlayRecord(Long playId);

	List<EndTimes> findPlayByDay(Long babyId, LocalDate createdDate);

	List<StatisticsTime> findPlayForWeek(Long babyId, LocalDate startDay, LocalDate endDay);
}
