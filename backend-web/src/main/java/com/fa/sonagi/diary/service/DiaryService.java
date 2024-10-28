package com.fa.sonagi.diary.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fa.sonagi.diary.dto.DiaryPostDto;
import com.fa.sonagi.diary.dto.DiaryPutDto;
import com.fa.sonagi.diary.dto.DiaryResDto;

public interface DiaryService {
	// 일기 작성 : 이미지 업로드 + 내용 업로드 + 생성.
	void createDiary(DiaryPostDto diaryPostDto, List<MultipartFile> imgFiles) throws Exception;

	// 일기 내용 + 사진 업데이트
	void updateDiaryContent(DiaryPutDto diaryPutDto, List<MultipartFile> imgFiles) throws Exception;

	// 일기 삭제
	void deleteDiary(Long diaryId);

	// 일별 일기 내용 조회
	DiaryResDto.DiaryInfos selectAllByBabyIdAndWriteDay(Long babyId, LocalDate writeDay) throws Exception;

	DiaryResDto.DiaryInfos selectAllByBabyId(Long babyId) throws Exception;

	// 월별 일기 기록 여부 날짜 리스트로 조회
	List<LocalDate> findAllDiaryByBabyId(Long babyId) throws Exception;

	DiaryResDto.DiaryInfo selectByDiaryId(Long diaryId);
}

