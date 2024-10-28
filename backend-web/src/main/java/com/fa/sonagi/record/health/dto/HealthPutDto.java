package com.fa.sonagi.record.health.dto;

import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HealthPutDto {

	@NotNull
	private Long healthId;

	@NotNull
	private LocalTime createdTime;
	private String memo;
}
