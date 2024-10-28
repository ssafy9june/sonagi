package com.fa.sonagi.record.activity.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
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
@Table(name = "tummytime",
	indexes = @Index(name = "idx_baby_id_created_date", columnList = "baby_id, created_date"))
public class Tummytime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tummytime_id", nullable = false)
	private Long id;

	@Column(name = "baby_id", nullable = false)
	private Long babyId;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "created_time", nullable = false)
	private LocalTime createdTime;

	@Column(name = "created_date", nullable = false)
	private LocalDate createdDate;

	@Column(name = "end_time", nullable = false)
	private LocalTime endTime;

	@Column(name = "memo", length = 100)
	private String memo;

	public void updateTummytime(LocalTime createdTime, LocalTime endTime, String memo) {
		this.createdTime = createdTime;
		this.endTime = endTime;
		this.memo = memo;
	}
}
