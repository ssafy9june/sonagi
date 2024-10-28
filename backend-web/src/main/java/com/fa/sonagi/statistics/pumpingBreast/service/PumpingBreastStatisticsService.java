package com.fa.sonagi.statistics.pumpingBreast.service;

import java.time.LocalDate;

import com.fa.sonagi.statistics.pumpingBreast.dto.PumpingBreastStatisticsResDto;
import com.fa.sonagi.statistics.pumpingBreast.dto.PumpingBreastStatisticsWeekResDto;

public interface PumpingBreastStatisticsService {

	PumpingBreastStatisticsResDto getPumpingBreastStatisticsDay(Long babyId, LocalDate createdDate);

	PumpingBreastStatisticsWeekResDto getPumpingBreastStatisticsWeek(Long babyId, LocalDate createdDate);
}
