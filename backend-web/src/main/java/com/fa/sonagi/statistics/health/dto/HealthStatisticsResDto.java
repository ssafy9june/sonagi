package com.fa.sonagi.statistics.health.dto;

import java.util.ArrayList;
import java.util.List;

import com.fa.sonagi.statistics.common.dto.Times;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HealthStatisticsResDto {
	private List<Times> times;
	private Double feverAvg;
	private Long hospitalCnt;
	private Long medicationCnt;
	private Long feverAvgPercent;
	private Long yesterdayFeverAvgPercent;
	private Long hospitalCntPercent;
	private Long yesterdayHospitalCntPercent;
	private Long medicationCntPercent;
	private Long yesterdayMedicationCntPercent;

	public HealthStatisticsResDto() {
		this.times = new ArrayList<>();
	}
}
