package com.fa.sonagi.record.health.entity;

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
@Table(name = "hospital",
	indexes = @Index(name = "idx_baby_id_created_date", columnList = "baby_id, created_date"))
public class Hospital {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "hospital_id", nullable = false)
	private Long id;

	@Column(name = "user_id", nullable = false)
	private Long userId;

	@Column(name = "baby_id", nullable = false)
	private Long babyId;

	@Column(name = "created_date", nullable = false)
	private LocalDate createdDate;

	@Column(name = "created_time", nullable = false)
	private LocalTime createdTime;

	@Column(name = "memo", length = 100)
	private String memo;

	public void updateHospital(LocalTime createdTime, String memo) {
		this.createdTime = createdTime;
		this.memo = memo;
	}
}