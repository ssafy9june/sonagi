package com.fa.sonagi.immunization.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class VaccinationResDto {
	private Long vaccinationStatusId;
	private Long vaccinationId;
	private String disease;
	private String vaccineName;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDate vaccinationDate;
	private String content1;
	private String title1;
	private String content2;
	private String title2;
	private String content3;
	private String title3;
	private String content4;
	private String title4;
	private String content5;
	private String title5;
	private String content6;
	private String title6;
}