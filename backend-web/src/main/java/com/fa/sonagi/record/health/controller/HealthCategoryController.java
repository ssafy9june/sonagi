package com.fa.sonagi.record.health.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fa.sonagi.record.health.dto.AllHealthResDto;
import com.fa.sonagi.record.health.dto.FeverResDto;
import com.fa.sonagi.record.health.dto.HealthResDto;
import com.fa.sonagi.record.health.service.HealthCategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "HealthCategory", description = "건강 카테고리 API")
@RequestMapping("/api/healthRecords")
@RestController
@RequiredArgsConstructor
public class HealthCategoryController {
	private final HealthCategoryService healthCategoryService;

	@GetMapping
	@Operation(summary = "아이 아이디에 해당하는 아이의 해당 날짜에 관한 건강 카테고리 기록들을 조회함")
	public ResponseEntity<?> getHealthRecord(@RequestParam Long babyId, @RequestParam LocalDate createdDate) {
		List<FeverResDto> feverList = healthCategoryService.findAllFever(babyId, createdDate);
		List<HealthResDto> medicationsList = healthCategoryService.findAllMedication(babyId, createdDate);
		List<HealthResDto> hospitalList = healthCategoryService.findAllHospital(babyId, createdDate);

		AllHealthResDto allHealthResDto = new AllHealthResDto();
		allHealthResDto.setAllHealthResDto(feverList, medicationsList, hospitalList);

		return ResponseEntity.ok().body(allHealthResDto);
	}
}
