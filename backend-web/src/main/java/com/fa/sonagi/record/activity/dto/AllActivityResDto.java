package com.fa.sonagi.record.activity.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class AllActivityResDto {
	private List<ActivityResDto> plays;
	private List<ActivityResDto> tummytimes;

	public AllActivityResDto() {
		this.plays = new ArrayList<>();
		this.tummytimes = new ArrayList<>();
	}

	public void setAllActivityResDto(List<ActivityResDto> plays, List<ActivityResDto> tummytimes) {
		this.plays = plays;
		this.tummytimes = tummytimes;
	}
}
