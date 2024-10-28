package com.fa.sonagi.diary.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DiaryPostDto {

	@NotNull
	private Long userId;

	@NotNull
	private Long babyId;

	private String content;

	private LocalDate writeDate;

	private LocalTime writeTime;
}
