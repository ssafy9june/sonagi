package com.fa.sonagi.statistics.meal.dto;

import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MealStatisticsQueryDto {
	private LocalTime createdTime;
	private Long amount;
}
