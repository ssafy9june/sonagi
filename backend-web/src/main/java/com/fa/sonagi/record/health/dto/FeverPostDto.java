package com.fa.sonagi.record.health.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeverPostDto {

	@NotNull
	private Long userId;

	@NotNull
	private Long babyId;

	@NotNull
	private LocalDate createdDate;

	@NotNull
	private LocalTime createdTime;

	private Double bodyTemperature;
	private String memo;
}