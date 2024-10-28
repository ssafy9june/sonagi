package com.fa.sonagi.statistics.activity.controller;

import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fa.sonagi.statistics.activity.dto.ActivityStatisticsResDto;
import com.fa.sonagi.statistics.activity.dto.ActivityStatisticsWeekResDto;
import com.fa.sonagi.statistics.activity.service.ActivityStatisticsService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "ActivityStatistics", description = "놀이 통계 API")
@RequestMapping("/api/activityStatistics")
@RestController
@RequiredArgsConstructor
public class ActivityStatisticsController {
	private final ActivityStatisticsService activityStatisticsService;

	/**
	 * 취침 통계 조회
	 */
	@GetMapping
	@Operation(summary = "아이 아이디에 해당하는 아이의 해당 날짜에 관한 주별 또는 일별 놀이 통계를 조회함")
	public ResponseEntity<?> getSleepStatistics(@RequestParam Long babyId,
		@RequestParam String period, @RequestParam LocalDate createdDate) {
		if (period.equals("day")) {
			ActivityStatisticsResDto activityStatisticsDay = activityStatisticsService.getActivityStatisticsDay(babyId, createdDate);
			return ResponseEntity.ok().body(activityStatisticsDay);
		}
		else if (period.equals("week")) {
			ActivityStatisticsWeekResDto activityStatisticsWeek = activityStatisticsService.getActivityStatisticsWeek(babyId, createdDate);
			return ResponseEntity.ok().body(activityStatisticsWeek);
		}
		else {
			return ResponseEntity.badRequest().build();
		}
	}
}
