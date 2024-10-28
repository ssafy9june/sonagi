package com.fa.sonagi.statistics.meal.service;

import java.time.LocalDate;

import com.fa.sonagi.statistics.meal.dto.MealStatisticsResDto;
import com.fa.sonagi.statistics.meal.dto.MealStatisticsWeekResDto;

public interface MealStatisticsService {

	MealStatisticsResDto getMealStatisticsDay(Long babyId, LocalDate createdDate);

	MealStatisticsWeekResDto getMealStatisticsWeek(Long babyId, LocalDate createdDate);
}
