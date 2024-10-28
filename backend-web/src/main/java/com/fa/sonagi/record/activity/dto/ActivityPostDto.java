package com.fa.sonagi.record.activity.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityPostDto {

	@NotNull
	private Long userId;

	@NotNull
	private Long babyId;

	@NotNull
	private LocalDate createdDate;

	@NotNull
	private LocalTime createdTime;

	@NotNull
	private LocalTime endTime;

	private String memo;
}
