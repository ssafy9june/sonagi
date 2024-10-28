package com.fa.sonagi.statistics.pumpingBreast.dto;

import java.util.ArrayList;
import java.util.List;

import com.fa.sonagi.statistics.common.dto.Times;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PumpingBreastStatisticsResDto {
	private List<Times> times;
	private Long cnt;
	private Long amount;
	private Long cntPercent;
	private Long yesterdayCntPercent;
	private Long amountPercent;
	private Long yesterdayAmountPercent;

	public PumpingBreastStatisticsResDto() {
		this.times = new ArrayList<>();
	}
}
