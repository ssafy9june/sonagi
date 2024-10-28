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
@Table(name = "checkup")
public class Checkup {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "checkup_id", nullable = false)
	private Long id;

	@Column(name = "checkup_name", length = 50)
	private String checkupName;

	@Column(name = "start_date")
	private int startDate;

	@Column(name = "end_date")
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
}
