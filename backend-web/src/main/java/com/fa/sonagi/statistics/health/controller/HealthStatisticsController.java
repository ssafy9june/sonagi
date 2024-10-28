package com.fa.sonagi.statistics.health.controller;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fa.sonagi.statistics.health.dto.HealthStatisticsResDto;
import com.fa.sonagi.statistics.health.dto.HealthStatisticsWeekResDto;
import com.fa.sonagi.statistics.health.service.HealthStatisticsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "HealthStatistics", description = "건강 통계 API")
@RequestMapping("/api/healthStatistics")
@RestController
@RequiredArgsConstructor
public class HealthStatisticsController {
	private final HealthStatisticsService healthStatisticsService;

	/**
	 * 건강 통계 조회
	 */
	@GetMapping
	@Operation(summary = "아이 아이디에 해당하는 아이의 해당 날짜에 관한 주별 또는 일별 건강 통계를 조회함")
	public ResponseEntity<?> getHealthStatistics(@RequestParam Long babyId,
		@RequestParam String period, @RequestParam LocalDate createdDate) {
		if (period.equals("day")) {
			HealthStatisticsResDto healthStatisticsDay = healthStatisticsService.getHealthStatisticsDay(babyId,
				createdDate);
			return ResponseEntity.ok().body(healthStatisticsDay);
		}
		else if (period.equals("week")) {
			HealthStatisticsWeekResDto healthStatisticsWeek = healthStatisticsService.getHealthStatisticsWeek(babyId,
				createdDate);
			return ResponseEntity.ok().body(healthStatisticsWeek);
		}
		else {
			return ResponseEntity.badRequest().build();
		}
	}
}
