package com.fa.sonagi.record.extra.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fa.sonagi.record.extra.dto.AllExtraResDto;
import com.fa.sonagi.record.extra.dto.ExtraResDto;
import com.fa.sonagi.record.extra.service.ExtraCategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "ExtraCategory", description = "기타 카테고리 API")
@RequestMapping("/api/extraRecords")
@RestController
@RequiredArgsConstructor
public class ExtraCategoryController {
	private final ExtraCategoryService extraCategoryService;

	/**
	 * 기타 카테고리 조회
	 */
	@GetMapping
	@Operation(summary = "아이 아이디에 해당하는 아이의 해당 날짜에 관한 기타 카테고리 기록들을 조회함")
	public ResponseEntity<?> getExtraRecord(@RequestParam Long babyId, @RequestParam LocalDate createdDate) {
		List<ExtraResDto> extraList = extraCategoryService.findAllExtra(babyId, createdDate);

		AllExtraResDto allExtraResDto = new AllExtraResDto();
		allExtraResDto.setAllExtraResDto(extraList);

		return ResponseEntity.ok().body(allExtraResDto);
	}

}
