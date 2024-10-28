package com.fa.sonagi.record.meal.dto;

import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SnackPutDto {

	@NotNull
	private Long mealId;

	private String memo;

	@NotNull
	private LocalTime createdTime;
}
