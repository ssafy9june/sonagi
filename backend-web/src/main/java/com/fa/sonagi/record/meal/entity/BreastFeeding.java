package com.fa.sonagi.record.meal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "breast_feeding",
	indexes = @Index(name = "idx_baby_id_created_date", columnList = "baby_id, created_date"))
public class BreastFeeding {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "breast_feeding_id")
	private Long id;

	@Column(name = "baby_id", nullable = false)
	private Long babyId;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "amount", nullable = false)
	private Long amount;

	@Column(name = "memo", length = 100)
	private String memo;

	@Column(name = "created_time", nullable = false)
	private LocalTime createdTime;

	@Column(name = "created_date", nullable = false)
	private LocalDate createdDate;

	public void updateBreastFeeding(Long amount, String memo, LocalTime createdTime) {
		this.amount = amount;
		this.memo = memo;
		this.createdTime = createdTime;
	}
}
