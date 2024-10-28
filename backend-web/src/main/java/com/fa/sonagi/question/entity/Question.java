package com.fa.sonagi.question.entity;

import java.time.LocalDate;

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
@Table(name = "question")
public class Question {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_id", nullable = false)
	private Long questionID;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "title", length = 50)
	private String title;

	@Column(name = "content", length = 255)
	private String content;

	@Column(name = "created_at", nullable = false)
	private LocalDate createdAt;
}
