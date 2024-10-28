package com.fa.sonagi.record.pumpingBreast.dto;

import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PumpingBreastResDto {
	private Long pumpingBreastId;
	private Long amount;
	private String memo;
	private LocalTime createdTime;
}
