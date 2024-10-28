package com.fa.sonagi.record.pumpingBreast.dto;

import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PumpingBreastPutDto {

	@NotNull
	private Long pumpingBreastId;

	@NotNull
	private Long amount;

	private String memo;

	@NotNull
	private LocalTime createdTime;
}
