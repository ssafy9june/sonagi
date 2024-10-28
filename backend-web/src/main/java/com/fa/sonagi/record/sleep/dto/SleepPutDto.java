package com.fa.sonagi.record.sleep.dto;

import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SleepPutDto {

	@NotNull
	private Long sleepId;

	@NotNull
	private LocalTime createdTime;

	@NotNull
	private LocalTime endTime;
	private String memo;
}
