package com.fa.sonagi.statistics.diaper.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiaperStatisticsDayForWeekDto {
	private List<Long> pees;
	private List<Long> poops;

	public DiaperStatisticsDayForWeekDto() {
		pees = new ArrayList<>();
		poops = new ArrayList<>();
	}
}
