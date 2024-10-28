package com.fa.sonagi.record.diaper.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fa.sonagi.record.diaper.dto.AllDiaperResDto;
import com.fa.sonagi.record.diaper.dto.DiaperResDto;
import com.fa.sonagi.record.diaper.service.DiaperCategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "DiaperCategory", description = "기저귀 카테고리 API")
@RequestMapping("/api/diaperRecords")
@RestController
@RequiredArgsConstructor
public class DiaperCategoryController {
	private final DiaperCategoryService diaperCategoryService;

	/**
	 * 기저귀 카테고리 조회
	 */
	@GetMapping
	@Operation(summary = "아이 아이디에 해당하는 아이의 해당 날짜에 관한 기저귀 카테고리 기록들을 조회함")
	public ResponseEntity<?> getDiaperRecord(@RequestParam Long babyId, @RequestParam LocalDate createdDate) {
		List<DiaperResDto> peeList = diaperCategoryService.findAllPee(babyId, createdDate);
		List<DiaperResDto> poopList = diaperCategoryService.findAllPoop(babyId, createdDate);

		AllDiaperResDto allDiaperResDto = new AllDiaperResDto();
		allDiaperResDto.setAllDiaperResDto(peeList, poopList);

		return ResponseEntity.ok().body(allDiaperResDto);
	}
}
