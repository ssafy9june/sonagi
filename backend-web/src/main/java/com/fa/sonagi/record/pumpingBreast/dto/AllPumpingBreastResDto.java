package com.fa.sonagi.record.pumpingBreast.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AllPumpingBreastResDto {
	private List<PumpingBreastResDto> pumpingBreasts;

	public AllPumpingBreastResDto() {
		this.pumpingBreasts = new ArrayList<>();
	}
}
