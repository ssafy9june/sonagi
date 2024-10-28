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
import com.fa.sonagi.record.activity.service.PlayServeice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/plays")
@Tag(name = "Play", description = "놀이 API")
@RestController
@RequiredArgsConstructor
public class PlayController {

  private final PlayServeice playServeice;

  /**
   * 놀이 기록 조회
   */
  @GetMapping("/{playId}")
  @Operation(summary = "놀이 기록에 관한 상세 정보를 조회함")
  public ResponseEntity<?> getPlay(@PathVariable Long playId) {
    ActivityResDto activityResDto = playServeice.findPlayById(playId);
    return ResponseEntity.ok().body(activityResDto);
  }

  /**
   * 놀이 기록 등록
   */
  @PostMapping
  @Operation(summary = "놀이 기록을 등록함")
  public ResponseEntity<?> registPlay(@RequestBody ActivityPostDto activityPostDto) {
    ActivityResDto activityResDto = playServeice.registPlay(activityPostDto);
    return ResponseEntity.ok().body(activityResDto);
  }

  /**
   * 놀이 기록 수정
   */
  @PutMapping
  @Operation(summary = "놀이 기록을 수정함")
  public ResponseEntity<?> updatePlay(@RequestBody ActivityPutDto activityPutDto) {
    playServeice.updatePlay(activityPutDto);
    return ResponseEntity.ok().build();
  }

  /**
   * 놀이 기록 삭제
   */
  @DeleteMapping("/{playId}")
  @Operation(summary = "놀이 기록을 삭제함")
  public ResponseEntity<?> deletePlay(@PathVariable Long playId) {
    playServeice.deletePlay(playId);
    return ResponseEntity.ok().build();
  }
}
