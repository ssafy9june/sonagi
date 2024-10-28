package com.fa.sonagi.statistics.extra.service;

import java.time.LocalDate;

import com.fa.sonagi.statistics.extra.dto.ExtraStatisticsResDto;
import com.fa.sonagi.statistics.extra.dto.ExtraStatisticsWeekResDto;

public interface ExtraStatisticsService {

	ExtraStatisticsResDto getExtraStatisticsDay(Long babyId, LocalDate createdDate);

	ExtraStatisticsWeekResDto getExtraStatisticsWeek(Long babyId, LocalDate createdDate);
}
