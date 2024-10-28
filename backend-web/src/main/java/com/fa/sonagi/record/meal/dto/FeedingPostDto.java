package com.fa.sonagi.record.meal.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedingPostDto {

	@NotNull
	private Long userId;

	@NotNull
	private Long babyId;

	@NotNull
	private LocalTime leftStartTime;

	@NotNull
	private LocalTime rightStartTime;

	@NotNull
	private LocalTime leftEndTime;

	@NotNull
	private LocalTime rightEndTime;

	private String memo;

	@NotNull
	private LocalDate createdDate;

	@NotNull
	private LocalTime createdTime;
}
