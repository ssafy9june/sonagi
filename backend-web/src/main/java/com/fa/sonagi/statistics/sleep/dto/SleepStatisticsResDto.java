package com.fa.sonagi.statistics.sleep.dto;

import java.util.ArrayList;
import java.util.List;

import com.fa.sonagi.statistics.common.dto.EndTimes;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SleepStatisticsResDto {
	private List<EndTimes> times;
	private Long cnt;
	private Long sleepHour;
	private Long sleepMinute;
	private Long cntPercent;
	private Long yesterdayCntPercent;
	private Long sleepPercent;
	private Long yesterdaySleepPercent;

	public SleepStatisticsResDto() {
		this.times = new ArrayList<>();
	}
}
