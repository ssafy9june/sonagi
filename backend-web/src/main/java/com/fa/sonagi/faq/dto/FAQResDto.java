package com.fa.sonagi.faq.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class FAQResDto {
	private Long faqId;
	private String category;
	private String title;
	private String content;
}
