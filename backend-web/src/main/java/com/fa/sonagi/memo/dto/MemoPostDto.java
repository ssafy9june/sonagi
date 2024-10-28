package com.fa.sonagi.memo.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemoPostDto {
	@NotNull
	private Long babyId;

	@NotNull
	private Long userId;

	@NotNull
	private String memo;
}
