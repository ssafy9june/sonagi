package com.fa.sonagi.statistics.sleep.dto;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SleepStatisticsWeekResDto {
	private Map<String, SleepStatisticsDayForWeekDto> sleepStatistics;
	private Long cnt;
	private Long sleepHour;
	private Long sleepMinute;
	private Long cntPercent;
	private Long yesterdayCntPercent;
	private Long sleepPercent;
	private Long yesterdaySleepPercent;

	public SleepStatisticsWeekResDto() {
		this.sleepStatistics =  new LinkedHashMap<>();
	}
}
