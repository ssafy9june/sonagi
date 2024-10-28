package com.fa.sonagi.record.health.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class AllHealthResDto {
	private List<FeverResDto> fevers;
	private List<HealthResDto> medications;
	private List<HealthResDto> hospitals;

	public AllHealthResDto() {
		this.fevers = new ArrayList<>();
		this.medications = new ArrayList<>();
		this.hospitals = new ArrayList<>();
	}

	public void setAllHealthResDto(List<FeverResDto> fevers, List<HealthResDto> medications,
		List<HealthResDto> hospitals) {
		this.fevers = fevers;
		this.medications = medications;
		this.hospitals = hospitals;
	}
}
