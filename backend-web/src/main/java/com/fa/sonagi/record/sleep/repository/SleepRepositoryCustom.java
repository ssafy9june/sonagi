package com.fa.sonagi.record.sleep.repository;

import java.time.LocalDate;
import java.util.List;

import com.fa.sonagi.record.allCategory.dto.StatisticsTime;
import com.fa.sonagi.record.sleep.dto.SleepResDto;
import com.fa.sonagi.statistics.common.dto.EndTimes;

public interface SleepRepositoryCustom {
	SleepResDto findSleepRecord(Long sleepId);

	List<EndTimes> findSleepByDay(Long babyId, LocalDate createdDate);

	List<StatisticsTime> findSleepForWeek(Long babyId, LocalDate startDay, LocalDate endDay);
}
