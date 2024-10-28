package com.fa.sonagi.diary.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "diary", indexes = @Index(name = "idx_baby_id_created_date", columnList = "baby_id,created_date"))
public class Diary {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "diary_id")
	private Long id;

	@Column(name = "baby_id", nullable = false)
	private Long babyId;

	@Column(name = "user_name", nullable = false)
	private String userName;

	@Column(name = "content")
	private String content;

	@OneToMany(mappedBy = "diary", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<DiaryFile> diaryFiles = new ArrayList<DiaryFile>();

	@Column(name = "created_date")
	private LocalDate createdDate;

	@Column(name = "created_time")
	private LocalTime createdTime;

	public void addDiaryFile(DiaryFile diaryFile) {
		diaryFiles.add(diaryFile);
		diaryFile.setDiary(this);
	}

	public void removeDiaryFile(DiaryFile diaryFile) {
		diaryFiles.remove(diaryFile);
		diaryFile.setDiary(null);
	}

	public void updateContent(String content) {
		this.content = content;
	}
}
