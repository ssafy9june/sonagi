package com.fa.sonagi.statistics.extra.dto;

import java.util.ArrayList;
import java.util.List;

import com.fa.sonagi.statistics.common.dto.Times;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExtraStatisticsResDto {
	private List<Times> times;
	private Long cnt;
	private Long cntPercent;
	private Long yesterdayCntPercent;

	public ExtraStatisticsResDto() {
		this.times = new ArrayList<>();
	}
}
