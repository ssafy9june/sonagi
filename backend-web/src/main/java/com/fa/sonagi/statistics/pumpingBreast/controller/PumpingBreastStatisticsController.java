package com.fa.sonagi.statistics.pumpingBreast.controller;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fa.sonagi.statistics.pumpingBreast.dto.PumpingBreastStatisticsResDto;
import com.fa.sonagi.statistics.pumpingBreast.dto.PumpingBreastStatisticsWeekResDto;
import com.fa.sonagi.statistics.pumpingBreast.service.PumpingBreastStatisticsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "PumpingBreastStatistics", description = "유축 통계 API")
@RequestMapping("/api/pumpingBreastStatistics")
@RestController
@RequiredArgsConstructor
public class PumpingBreastStatisticsController {
	private final PumpingBreastStatisticsService pumpingBreastStatisticsService;

	/**
	 * 유축 통계 조회
	 */
	@GetMapping
	@Operation(summary = "아이 아이디에 해당하는 아이의 해당 날짜에 관한 주별 또는 일별 유축 통계를 조회함")
	public ResponseEntity<?> getPumpingBreastStatistics(@RequestParam Long babyId,
		@RequestParam String period, @RequestParam LocalDate createdDate) {
		if (period.equals("day")) {
			PumpingBreastStatisticsResDto pumpingBreastStatisticsDay = pumpingBreastStatisticsService.getPumpingBreastStatisticsDay(
				babyId, createdDate);
			return ResponseEntity.ok().body(pumpingBreastStatisticsDay);
		}
		else if (period.equals("week")) {
			PumpingBreastStatisticsWeekResDto pumpingBreastStatisticsWeek = pumpingBreastStatisticsService.getPumpingBreastStatisticsWeek(
				babyId, createdDate);
			return ResponseEntity.ok().body(pumpingBreastStatisticsWeek);
		}
		else {
			return ResponseEntity.badRequest().build();
		}
	}
}
