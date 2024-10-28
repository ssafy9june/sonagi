package com.fa.sonagi.baby.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "baby")
public class Baby {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "baby_id")
	private Long id;

	@Column(name = "birth_date", nullable = false)
	private LocalDate birthDate;

	@Column(name = "gender", nullable = false, length = 2)
	private String gender;

	@Column(name = "name", nullable = false, length = 25)
	private String name;

	@Column(name = "code")
	private String code;

	@Column(name = "last_meal_time")
	private LocalDateTime lastMealTime;

	@Column(name = "last_diary_time")
	private LocalDateTime lastDiaryTime;

	@Column(name = "meal_notification", nullable = false, columnDefinition = "VARCHAR(1) default 'N'")
	private String mealNotification;

	@Column(name = "deleted_at")
	private LocalDate deletedAt;

	@Column(name = "is_deleted", nullable = false, length = 2)
	private String isDeleted;

	@PrePersist
	public void setDefaultValues() {
		if (mealNotification == null) {
			mealNotification = "N";
		}
	}

	public void updateMealOn(String mealNotification) {
		this.mealNotification = mealNotification;
	}

	public void updateCode(String code) {
		this.code = code;
	}

	public void updateBaby(String name, String gender, LocalDate birthDate) {
		this.name = name;
		this.gender = gender;
		this.birthDate = birthDate;
	}

	public void deleteBabyInfo(LocalDate deletedAt, String isDeleted) {
		this.deletedAt = deletedAt;
		this.isDeleted = isDeleted;
	}

	public void updateLastMealTime(LocalDateTime lastMealTime) {
		this.lastMealTime = lastMealTime;
	}

	public void updateLastDiaryTime(LocalDateTime lastDiaryTime) {
		this.lastDiaryTime = lastDiaryTime;
	}

}
