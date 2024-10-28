package com.fa.sonagi.record.meal.dto;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedingResDto {
	private Long mealId;
	private LocalTime leftStartTime;
	private LocalTime rightStartTime;
	private LocalTime leftEndTime;
	private LocalTime rightEndTime;
	private LocalTime createdTime;
	private String memo;
}
