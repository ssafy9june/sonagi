package com.fa.sonagi.record.sleep.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fa.sonagi.record.sleep.dto.SleepPostDto;
import com.fa.sonagi.record.sleep.dto.SleepPutDto;
import com.fa.sonagi.record.sleep.dto.SleepResDto;
import com.fa.sonagi.record.sleep.service.SleepService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/sleeps")
@Tag(name = "Sleep", description = "수면 API")
@RestController
@RequiredArgsConstructor
public class SleepController {
	private final SleepService sleepService;

	/**
	 * 수면 기록 조회
	 */
	@GetMapping("/{sleepId}")
	@Operation(summary = "수면 기록에 관한 상세 정보를 조회함")
	public ResponseEntity<?> getSleep(@PathVariable Long sleepId) {
		SleepResDto sleepResDto = sleepService.findSleepById(sleepId);
		return ResponseEntity.ok().body(sleepResDto);
	}

	/**
	 * 수면 기록 등록
	 */
	@PostMapping
	@Operation(summary = "수면 기록을 등록함")
	public ResponseEntity<?> registSleep(@RequestBody SleepPostDto sleepPostDto) {
		SleepResDto sleepResDto = sleepService.registSleep(sleepPostDto);
		return ResponseEntity.ok().body(sleepResDto);
	}

	/**
	 * 수면 기록 수정
	 */
	@PutMapping
	@Operation(summary = "수면 기록을 수정함")
	public ResponseEntity<?> updateSleep(@RequestBody SleepPutDto sleepPutDto) {
		sleepService.updateSleep(sleepPutDto);
		return ResponseEntity.ok().build();
	}

	/**
	 * 수면 기록 삭제
	 */
	@DeleteMapping("/{sleepId}")
	@Operation(summary = "수면 기록을 삭제함")
	public ResponseEntity<?> deleteSleep(@PathVariable Long sleepId) {
		sleepService.deleteSleep(sleepId);
		return ResponseEntity.ok().build();
	}
}
