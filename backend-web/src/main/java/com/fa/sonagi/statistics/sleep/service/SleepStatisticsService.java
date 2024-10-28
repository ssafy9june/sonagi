package com.fa.sonagi.statistics.sleep.service;

import java.time.LocalDate;

import com.fa.sonagi.statistics.sleep.dto.SleepStatisticsResDto;
import com.fa.sonagi.statistics.sleep.dto.SleepStatisticsWeekResDto;

public interface SleepStatisticsService {

	SleepStatisticsResDto getSleepStatisticsDay(Long babyId, LocalDate createdDate);

	SleepStatisticsWeekResDto getSleepStatisticsWeek(Long babyId, LocalDate createdDate);
}
