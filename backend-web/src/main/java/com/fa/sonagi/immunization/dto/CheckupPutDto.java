package com.fa.sonagi.immunization.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckupPutDto {
	@NotNull
	private Long checkupStatusId;

	@NotNull
	private LocalDate checkupDate;
}
