package com.fa.sonagi.immunization.entity;

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
@Table(name = "vaccination")
public class Vaccination {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "vaccination_id", nullable = false)
	private Long id;

	@Column(name = "disease", length = 50, nullable = false)
	private String disease;

	@Column(name = "vaccine_name", length = 50, nullable = false)
	private String vaccineName;

	@Column(name = "start_date", nullable = false)
	private int startDate;

	@Column(name = "end_date", nullable = false)
	private int endDate;

	@Column(name = "content1", length = 1000)
	private String content1;

	@Column(name = "title1", length = 200)
	private String title1;

	@Column(name = "content2", length = 1000)
	private String content2;

	@Column(name = "title2", length = 200)
	private String title2;

	@Column(name = "content3", length = 1000)
	private String content3;

	@Column(name = "title3", length = 200)
	private String title3;

	@Column(name = "content4", length = 1000)
	private String content4;

	@Column(name = "title4", length = 200)
	private String title4;

	@Column(name = "content5", length = 1000)
	private String content5;

	@Column(name = "title5", length = 200)
	private String title5;

	@Column(name = "content6", length = 1000)
	private String content6;

	@Column(name = "title6", length = 200)
	private String title6;
}