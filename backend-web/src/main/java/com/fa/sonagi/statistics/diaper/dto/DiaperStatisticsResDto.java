package com.fa.sonagi.statistics.diaper.dto;

import java.util.ArrayList;
import java.util.List;

import com.fa.sonagi.statistics.common.dto.Times;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiaperStatisticsResDto {
	private List<Times> times;
	private Long peeCnt;
	private Long poopCnt;
	private Long peeCntPercent;
	private Long yesterdayPeeCntPercent;
	private Long poopCntPercent;
	private Long yesterdayPoopCntPercent;

	public DiaperStatisticsResDto() {
		this.times = new ArrayList<>();
	}
}
