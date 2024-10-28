package com.fa.sonagi.statistics.activity.dto;

import java.util.ArrayList;
import java.util.List;

import com.fa.sonagi.record.allCategory.dto.StatisticsTimeLong;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityStatisticsDayForWeekDto {
	private List<StatisticsTimeLong> plays;
	private List<StatisticsTimeLong> tummytimes;

	public ActivityStatisticsDayForWeekDto() {
		this.plays = new ArrayList<>();
		this.tummytimes = new ArrayList<>();
	}
}
