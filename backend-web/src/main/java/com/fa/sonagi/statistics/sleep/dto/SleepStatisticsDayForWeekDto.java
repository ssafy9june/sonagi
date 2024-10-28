package com.fa.sonagi.statistics.sleep.dto;

import java.util.ArrayList;
import java.util.List;

import com.fa.sonagi.record.allCategory.dto.StatisticsTimeLong;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SleepStatisticsDayForWeekDto {
	private List<StatisticsTimeLong> sleeps;

	public SleepStatisticsDayForWeekDto() {
		this.sleeps = new ArrayList<>();
	}
}
