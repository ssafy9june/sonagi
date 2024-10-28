package com.fa.sonagi.statistics.health.service;

import java.time.LocalDate;

import com.fa.sonagi.statistics.health.dto.HealthStatisticsResDto;
import com.fa.sonagi.statistics.health.dto.HealthStatisticsWeekResDto;

public interface HealthStatisticsService {

	HealthStatisticsResDto getHealthStatisticsDay(Long babyId, LocalDate createdDate);

	HealthStatisticsWeekResDto getHealthStatisticsWeek(Long babyId, LocalDate createdDate);
}
