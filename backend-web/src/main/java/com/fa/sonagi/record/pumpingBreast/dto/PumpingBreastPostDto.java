package com.fa.sonagi.record.pumpingBreast.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PumpingBreastPostDto {

	@NotNull
	private Long userId;

	@NotNull
	private Long babyId;

	@NotNull
	private Long amount;

	private String memo;

	@NotNull
	private LocalTime createdTime;

	@NotNull
	private LocalDate createdDate;
}
