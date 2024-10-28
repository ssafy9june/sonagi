package com.fa.sonagi.statistics.extra.dto;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExtraStatisticsWeekResDto {
	private Map<String, ExtraStatisticsDayForWeekDto> extraStatistics;
	private Long cnt;
	private Long cntPercent;
	private Long yesterdayCntPercent;

	public ExtraStatisticsWeekResDto() {
		this.extraStatistics = new LinkedHashMap<>();
	}
}
