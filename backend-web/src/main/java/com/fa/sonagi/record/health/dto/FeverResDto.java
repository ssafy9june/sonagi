package com.fa.sonagi.record.health.dto;

import java.time.LocalTime;

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
public class FeverResDto {
	private Long healthId;
	private LocalTime createdTime;
	private Double bodyTemperature;
	private String memo;
}
