package com.fa.sonagi.record.activity.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fa.sonagi.record.activity.dto.ActivityPostDto;
import com.fa.sonagi.record.activity.dto.ActivityPutDto;
import com.fa.sonagi.record.activity.dto.ActivityResDto;
import com.fa.sonagi.record.activity.service.TummytimeService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Tummytime", description = "터미타임 API")
@RequestMapping("/api/tummytimes")
@RestController
@RequiredArgsConstructor
public class TummytimeController {

  private final TummytimeService tummytimeService;

  /**
   * 터미타임 기록 조회
   */
  @GetMapping("/{tummytimeId}")
  @Operation(summary = "터미타임 기록에 관한 상세 정보를 조회함")
  public ResponseEntity<?> getTummytime(@PathVariable Long tummytimeId) {
    ActivityResDto activityResDto = tummytimeService.findTummytimeById(tummytimeId);
    return ResponseEntity.ok().body(activityResDto);
  }

  /**
   * 터미타임 기록 등록
   */
  @PostMapping
  @Operation(summary = "터미타임 기록을 등록함")
  public ResponseEntity<?> registTummytime(@RequestBody ActivityPostDto activityPostDto) {
    ActivityResDto activityResDto = tummytimeService.registTummytime(activityPostDto);
    return ResponseEntity.ok().body(activityResDto);
  }

  /**
   * 터미타임 기록 수정
   */
  @PutMapping
  @Operation(summary = "터미타임 기록을 수정함")
  public ResponseEntity<?> updateTummytime(@RequestBody ActivityPutDto activityPutDto) {
    tummytimeService.updateTummytime(activityPutDto);
    return ResponseEntity.ok().build();
  }

  /**
   * 터미타임 기록 삭제
   */
  @DeleteMapping("/{tummytimeId}")
  @Operation(summary = "터미타임 기록을 삭제함")
  public ResponseEntity<?> deleteTummytime(@PathVariable Long tummytimeId) {
    tummytimeService.deleteTummytime(tummytimeId);
    return ResponseEntity.ok().build();
  }
}
