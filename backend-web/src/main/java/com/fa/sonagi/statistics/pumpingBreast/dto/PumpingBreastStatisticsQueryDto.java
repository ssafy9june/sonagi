package com.fa.sonagi.statistics.pumpingBreast.dto;

import java.time.LocalTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PumpingBreastStatisticsQueryDto {
	private LocalTime createdTime;
	private Long amount;
}
