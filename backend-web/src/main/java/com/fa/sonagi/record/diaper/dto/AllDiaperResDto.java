package com.fa.sonagi.record.diaper.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class AllDiaperResDto {
	private List<DiaperResDto> pees;
	private List<DiaperResDto> poops;

	public AllDiaperResDto() {
		this.pees = new ArrayList<>();
		this.poops = new ArrayList<>();
	}

	public void setAllDiaperResDto(List<DiaperResDto> pees, List<DiaperResDto> poops) {
		this.pees = pees;
		this.poops = poops;
	}
}
