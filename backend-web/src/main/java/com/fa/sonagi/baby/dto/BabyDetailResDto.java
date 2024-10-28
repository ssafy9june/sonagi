package com.fa.sonagi.baby.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BabyDetailResDto {
	private Long babyId;
	private String name;
	private LocalDate birthDate;
	private String gender;
	private String authority;
	private String isDeleted;
	private LocalDate deletedAt;
}
