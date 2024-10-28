package com.fa.sonagi.statistics.diaper.dto;

import java.util.LinkedHashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiaperStatisticsWeekResDto {
	private Map<String, DiaperStatisticsDayForWeekDto> diaperStatistics;
	private Long peeCnt;
	private Long poopCnt;
	private Long peeCntPercent;
	private Long yesterdayPeeCntPercent;
	private Long poopCntPercent;
	private Long yesterdayPoopCntPercent;

	public DiaperStatisticsWeekResDto() {
		this.diaperStatistics = new LinkedHashMap<>();
	}
}
