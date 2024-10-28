package com.fa.sonagi.diary.controller;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fa.sonagi.diary.dto.DiaryPostDto;
import com.fa.sonagi.diary.dto.DiaryPutDto;
import com.fa.sonagi.diary.dto.DiaryResDto;
import com.fa.sonagi.diary.service.DiaryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/diaries")
@Tag(name = "Diary", description = "일기 CRUD API")
@RequiredArgsConstructor
public class DiaryController {

	private final DiaryService diaryService;

	@GetMapping
	@Operation(summary = "일별 아이의 일기 데이터 조회")
	public ResponseEntity<?> getDiaries(@RequestParam Long babyId, @RequestParam LocalDate writeDay) throws Exception {
		DiaryResDto.DiaryInfos diaryInfos = diaryService.selectAllByBabyIdAndWriteDay(babyId, writeDay);

		return ResponseEntity.ok().body(diaryInfos);
	}
	@GetMapping("/all")
	@Operation(summary = "아이의 모든 일기 데이터 조회")
	public ResponseEntity<?> getAllDiariy(@RequestParam Long babyId) throws Exception {
		DiaryResDto.DiaryInfos diaryInfos = diaryService.selectAllByBabyId(babyId);
		return ResponseEntity.ok().body(diaryInfos);
	}


	@PutMapping
	@Operation(summary = "일기 내용, 사진 데이터 수정")
	public ResponseEntity<?> updateDiaries(@RequestPart DiaryPutDto diaryPutDto,
		@RequestPart(required = false) List<MultipartFile> imgFiles) throws Exception {
		diaryService.updateDiaryContent(diaryPutDto, imgFiles);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{diaryId}")
	@Operation(summary = "일기 삭제")
	public ResponseEntity<?> deleteDiary(@PathVariable Long diaryId) {
		diaryService.deleteDiary(diaryId);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/dates")
	@Operation(summary = "월별 일기 기록 여부 날짜 리스트 조회")
	public ResponseEntity<?> getDateListByBabyId(
		@Parameter(description = "아이 Id", required = true) @RequestParam Long babyId) throws Exception {
		return ResponseEntity.ok().body(new DiaryResDto.DateInfos(diaryService.findAllDiaryByBabyId(babyId)));
	}

	@GetMapping("/{diaryId}")
	@Operation(summary = "일기 id를 통한 조회")
	public ResponseEntity<?> getDiaryById(@PathVariable Long diaryId) {
		DiaryResDto.DiaryInfo diaryInfo = diaryService.selectByDiaryId(diaryId);
		return ResponseEntity.ok().body(diaryInfo);
	}

	@PostMapping
	@Operation(summary = "일기 등록")
	public ResponseEntity<?> addDiary(@RequestParam(value = "imgFiles", required = false) List<MultipartFile> imgFiles,
		@RequestParam("userId") Long userId, @RequestParam("babyId") Long babyId,
		@RequestParam("content") String content, @RequestParam("writeDate") LocalDate writeDate,
		@RequestParam("writeTime") LocalTime writeTime) throws Exception {

		DiaryPostDto diaryPostDto = DiaryPostDto.builder()
		                                        .writeDate(writeDate)
		                                        .userId(userId)
		                                        .writeTime(writeTime)
		                                        .babyId(babyId)
		                                        .content(content)
		                                        .build();
		diaryService.createDiary(diaryPostDto, imgFiles);
		return ResponseEntity.ok().build();
	}
}
