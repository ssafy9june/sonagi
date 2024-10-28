package com.fa.sonagi.statistics.activity.dto;

import java.util.ArrayList;
import java.util.List;

import com.fa.sonagi.statistics.common.dto.EndTimes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityStatisticsResDto {
	private List<EndTimes> times;
	private Long cnt;
	private Long activityHour;
	private Long activityMinute;
	private Long cntPercent;
	private Long yesterdayCntPercent;
	private Long activityPercent;
	private Long yesterdayActivityPercent;

	public ActivityStatisticsResDto() {
		this.times = new ArrayList<>();
	}
}
