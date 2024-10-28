package com.fa.sonagi.record.extra.dto;

import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExtraPutDto {

	@NotNull
	private Long extraId;

	@NotNull
	private LocalTime createdTime;
	private String memo;
}
