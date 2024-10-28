package com.fa.sonagi.statistics.health.dto;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HealthStatisticsWeekResDto {
	private Map<String, HealthStatisticsDayForWeekDto> healthStatistics;
	private Double feverAvg;
	private Long hospitalCnt;
	private Long medicationCnt;
	private Long feverAvgPercent;
	private Long yesterdayFeverAvgPercent;
	private Long hospitalCntPercent;
	private Long yesterdayHospitalCntPercent;
	private Long medicationCntPercent;
	private Long yesterdayMedicationCntPercent;

	public HealthStatisticsWeekResDto() {
		this.healthStatistics = new LinkedHashMap<>();
	}
}
