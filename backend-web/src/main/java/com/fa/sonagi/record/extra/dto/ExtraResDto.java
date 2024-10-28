package com.fa.sonagi.record.extra.dto;

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
public class ExtraResDto {
	private Long extraId;
	private LocalTime createdTime;
	private String memo;
}
