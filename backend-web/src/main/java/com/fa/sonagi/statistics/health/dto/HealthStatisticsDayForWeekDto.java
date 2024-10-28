package com.fa.sonagi.statistics.health.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HealthStatisticsDayForWeekDto {
	private Double feverAvg;
	private Long hospitalCnt;
	private Long medicationCnt;
}
