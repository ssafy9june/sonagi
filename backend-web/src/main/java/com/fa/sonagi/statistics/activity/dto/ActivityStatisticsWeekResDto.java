package com.fa.sonagi.statistics.activity.dto;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityStatisticsWeekResDto {
	private Map<String, ActivityStatisticsDayForWeekDto> activityStatistics;
	private Long cnt;
	private Long activityHour;
	private Long activityMinute;
	private Long cntPercent;
	private Long yesterdayCntPercent;
	private Long activityPercent;
	private Long yesterdayActivityPercent;

	public ActivityStatisticsWeekResDto() {
		this.activityStatistics = new LinkedHashMap<>();
	}
}
