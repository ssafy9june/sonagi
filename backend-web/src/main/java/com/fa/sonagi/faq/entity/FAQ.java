package com.fa.sonagi.faq.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "faq")
public class FAQ {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "faq_id", nullable = false)
	private Long faqId;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "category", nullable = false)
	private String category;

	@Column(name = "title", length = 40, nullable = false)
	private String title;

	@Column(name = "content", length = 255, nullable = false)
	private String content;

	public void updateFAQ(String category, String title, String content) {
		this.category = category;
		this.title = title;
		this.content = content;
	}
}
