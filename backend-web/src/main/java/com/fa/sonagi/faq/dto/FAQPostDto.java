package com.fa.sonagi.faq.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FAQPostDto {
	@NotNull
	private Long userId;

	@NotNull
	private String category;

	@NotNull
	private String title;

	@NotNull
	private String content;
}
