package com.fa.sonagi.record.meal.entity;

import jakarta.persistence.*;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "baby_food",
	indexes = @Index(name = "idx_baby_id_created_date", columnList = "baby_id, created_date"))
public class BabyFood {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "baby_food_id")
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

	public void updateBabyFood(Long amount, String memo, LocalTime createdTime) {
		this.amount = amount;
		this.memo = memo;
		this.createdTime = createdTime;
	}
}
