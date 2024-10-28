package com.fa.sonagi.statistics.extra.controller;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fa.sonagi.statistics.extra.dto.ExtraStatisticsResDto;
import com.fa.sonagi.statistics.extra.dto.ExtraStatisticsWeekResDto;
import com.fa.sonagi.statistics.extra.service.ExtraStatisticsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "ExtraStatistics", description = "기타 통계 API")
@RequestMapping("/api/extraStatistics")
@RestController
@RequiredArgsConstructor
public class ExtraStatisticsController {
	private final ExtraStatisticsService extraStatisticsService;

	/**
	 * 기타 통계 조회
	 */
	@GetMapping
	@Operation(summary = "아이 아이디에 해당하는 아이의 해당 날짜에 관한 주별 또는 일별 기타 통계를 조회함")
	public ResponseEntity<?> getExtraStatistics(@RequestParam Long babyId,
		@RequestParam String period, @RequestParam LocalDate createdDate) {
		if (period.equals("day")) {
			ExtraStatisticsResDto extraStatisticsDay = extraStatisticsService.getExtraStatisticsDay(babyId,
				createdDate);
			return ResponseEntity.ok().body(extraStatisticsDay);
		}
		else if (period.equals("week")) {
			ExtraStatisticsWeekResDto extraStatisticsWeek = extraStatisticsService.getExtraStatisticsWeek(babyId, createdDate);
			return ResponseEntity.ok().body(extraStatisticsWeek);
		}
		else {
			return ResponseEntity.badRequest().build();
		}
	}
}
