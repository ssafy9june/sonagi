package com.fa.sonagi.record.diaper.dto;

import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiaperPutDto {

	@NotNull
	private Long diaperId;

	@NotNull
	private LocalTime createdTime;
	private String memo;
}