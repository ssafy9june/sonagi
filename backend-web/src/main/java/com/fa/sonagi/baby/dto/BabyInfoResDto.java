package com.fa.sonagi.baby.dto;

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
public class BabyInfoResDto {
	private Long babyId;
	private String name;
	private String gender;
	private String authority;
}
