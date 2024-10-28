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
public class SnackResDto {
	private Long mealId;
	private String memo;
	private LocalTime createdTime;
}
