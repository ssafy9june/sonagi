package com.fa.sonagi.record.health.dto;

import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeverPutDto {

	@NotNull
	private Long healthId;

	@NotNull
	private LocalTime createdTime;

	@NotNull
	private Double bodyTemperature;
	private String memo;
}