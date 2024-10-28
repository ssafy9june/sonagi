package com.fa.sonagi.record.diaper.dto;

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
public class DiaperResDto {
	private Long diaperId;
	private LocalTime createdTime;
	private String memo;
}
