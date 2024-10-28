package com.fa.sonagi.diary.dto;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiaryPutDto {
	@NotNull
	private Long diaryId;

	private String content;

	private List<String> removeFiles;
}
