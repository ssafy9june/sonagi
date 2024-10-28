package com.fa.sonagi.diary.dto;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

public class DiaryResDto {

	@Getter
	public static class DiaryInfos {
		private List<DiaryInfo> diaries;

		public DiaryInfos() {
			this.diaries = new ArrayList<>();
		}

		public void setDiaries(List<DiaryInfo> diaries) {
			this.diaries = diaries;
		}
	}

	@Getter
	@Setter
	@Builder
	public static class DiaryInfo {
		private Long diaryId;
		private String userName;
		private LocalTime writedTime;
		private List<String> imgUrls;
		private String content;
	}

	@Getter
	@Setter
	@AllArgsConstructor
	public static class DateInfos {
		private List<LocalDate> dateList;
	}

}
