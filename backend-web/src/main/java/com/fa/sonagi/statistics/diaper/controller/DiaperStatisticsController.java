package com.fa.sonagi.statistics.diaper.controller;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fa.sonagi.statistics.diaper.dto.DiaperStatisticsResDto;
import com.fa.sonagi.statistics.diaper.dto.DiaperStatisticsWeekResDto;
import com.fa.sonagi.statistics.diaper.service.DiaperStatisticsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "DiaperStatistics", description = "기저귀 통계 API")
@RequestMapping("/api/diaperStatistics")
@RestController
@RequiredArgsConstructor
public class DiaperStatisticsController {
	private final DiaperStatisticsService diaperStatisticsService;

	/**
	 * 기저귀 통계 조회
	 */
	@GetMapping
	@Operation(summary = "아이 아이디에 해당하는 아이의 해당 날짜에 관한 주별 또는 일별 기저귀 통계를 조회함")
	public ResponseEntity<?> getDiaperStatistics(@RequestParam Long babyId,
		@RequestParam String period, @RequestParam LocalDate createdDate) {
		if (period.equals("day")) {
			DiaperStatisticsResDto diaperStatisticsDay = diaperStatisticsService.getDiaperStatisticsDay(babyId, createdDate);
			return ResponseEntity.ok().body(diaperStatisticsDay);
		}
		else if (period.equals("week")) {
			DiaperStatisticsWeekResDto diaperStatisticsWeek = diaperStatisticsService.getDiaperStatisticsWeek(babyId, createdDate);
			return ResponseEntity.ok().body(diaperStatisticsWeek);
		}
		else {
			return ResponseEntity.badRequest().build();
		}
	}
}
