package com.fa.sonagi.statistics.pumpingBreast.dto;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PumpingBreastStatisticsWeekResDto {
	private Map<String, PumpingBreastStatisticsDayForWeekDto> pumpingBreastStatistics;
	private Long cnt;
	private Long amount;
	private Long cntPercent;
	private Long yesterdayCntPercent;
	private Long amountPercent;
	private Long yesterdayAmountPercent;

	public PumpingBreastStatisticsWeekResDto() {
		this.pumpingBreastStatistics = new LinkedHashMap<>();
	}
}
