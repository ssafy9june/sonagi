package com.fa.sonagi.baby.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BabyInfoPostDto {

	@NotNull
	private LocalDate birthDate;

	@NotNull
	private String gender;

	@NotNull
	private String name;

	@NotNull
	private Long userId;
}
