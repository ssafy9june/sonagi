package com.fa.sonagi.immunization.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CheckupResDto {
	private Long checkupStatusId;
	private Long checkupId;
	private String checkupName;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDate checkupDate;
	private String content1;
	private String title1;
	private String content2;
	private String title2;
	private String content3;
	private String title3;
	private String content4;
	private String title4;

}