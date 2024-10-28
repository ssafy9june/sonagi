package com.fa.sonagi.statistics.diaper.service;

import java.time.LocalDate;
import com.fa.sonagi.statistics.diaper.dto.DiaperStatisticsResDto;
import com.fa.sonagi.statistics.diaper.dto.DiaperStatisticsWeekResDto;

public interface DiaperStatisticsService {

	DiaperStatisticsResDto getDiaperStatisticsDay(Long babyId, LocalDate createdDate);

	DiaperStatisticsWeekResDto getDiaperStatisticsWeek(Long babyId, LocalDate createdDate);
}
