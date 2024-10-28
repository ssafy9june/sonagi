package com.fa.sonagi.baby.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BabyDetailPutDto {
	@NotNull
	private Long babyId;

	@NotNull
	private String name;

	@NotNull
	private String gender;

	@NotNull
	private LocalDate birthDate;
}
