package com.fa.sonagi.record.extra.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class AllExtraResDto {
	private List<ExtraResDto> extras;

	public AllExtraResDto() {
		this.extras = new ArrayList<>();
	}

	public void setAllExtraResDto(List<ExtraResDto> extras) {
		this.extras = extras;
	}
}
