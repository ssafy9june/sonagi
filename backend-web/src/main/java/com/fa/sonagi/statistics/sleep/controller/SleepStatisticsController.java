package com.fa.sonagi.statistics.sleep.controller;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fa.sonagi.statistics.sleep.dto.SleepStatisticsResDto;
import com.fa.sonagi.statistics.sleep.dto.SleepStatisticsWeekResDto;
import com.fa.sonagi.statistics.sleep.service.SleepStatisticsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "SleepStatistics", description = "취침 통계 API")
@RequestMapping("/api/sleepStatistics")
@RestController
@RequiredArgsConstructor
public class SleepStatisticsController {
	private final SleepStatisticsService sleepStatisticsService;

	/**
	 * 취침 통계 조회
	 */
	@GetMapping
	@Operation(summary = "아이 아이디에 해당하는 아이의 해당 날짜에 관한 주별 또는 일별 취침 통계를 조회함")
	public ResponseEntity<?> getSleepStatistics(@RequestParam Long babyId,
		@RequestParam String period, @RequestParam LocalDate createdDate) {
		if (period.equals("day")) {
			SleepStatisticsResDto sleepStatisticsDay = sleepStatisticsService.getSleepStatisticsDay(babyId,
				createdDate);
			return ResponseEntity.ok().body(sleepStatisticsDay);
		}
		else if (period.equals("week")){
			SleepStatisticsWeekResDto sleepStatisticsWeek = sleepStatisticsService.getSleepStatisticsWeek(babyId,
				createdDate);
			return ResponseEntity.ok().body(sleepStatisticsWeek);
		}
		else {
			return ResponseEntity.badRequest().build();
		}
	}
}
