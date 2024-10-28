package com.fa.sonagi.immunization.entity;

import java.time.LocalDate;

import com.fa.sonagi.baby.entity.Baby;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "checkup_status")
public class CheckupStatus {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "checkup_status_id", nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "baby_id")
	private Baby baby;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "checkup_id")
	private Checkup checkup;

	@Column(name = "checkup_date")
	private LocalDate checkupDate;

	public void updateCheckup(LocalDate checkupDate) {
		this.checkupDate = checkupDate;
	}

}
