package com.fa.sonagi.record.meal.dto;

import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedingPutDto {
	@NotNull
	private Long mealId;

	@NotNull
	private LocalTime leftStartTime;

	@NotNull
	private LocalTime rightStartTime;

	@NotNull
	private LocalTime leftEndTime;

	@NotNull
	private LocalTime rightEndTime;

	private String memo;
}
