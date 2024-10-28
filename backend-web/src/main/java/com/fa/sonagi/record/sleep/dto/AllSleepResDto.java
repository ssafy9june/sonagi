package com.fa.sonagi.record.sleep.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class AllSleepResDto {
	private List<SleepResDto> sleeps;

	public AllSleepResDto() {
		this.sleeps = new ArrayList<>();
	}

	public void setAllSleepResDto(List<SleepResDto> sleeps) {
		this.sleeps = sleeps;
	}
}
