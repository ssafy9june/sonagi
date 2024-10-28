package com.fa.sonagi.record.extra.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fa.sonagi.record.extra.dto.ExtraPostDto;
import com.fa.sonagi.record.extra.dto.ExtraPutDto;
import com.fa.sonagi.record.extra.dto.ExtraResDto;
import com.fa.sonagi.record.extra.service.ExtraService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/extras")
@Tag(name = "Extra", description = "기타 API")
@RestController
@RequiredArgsConstructor
public class ExtraController {

  private final ExtraService extraService;

  /**
   * 기타 기록 조회
   */
  @GetMapping("/{extraId}")
  @Operation(summary = "기타 기록에 관한 상세 정보를 조회함")
  public ResponseEntity<?> getExtra(@PathVariable Long extraId) {
    ExtraResDto extraResDto = extraService.findExtraById(extraId);
    return ResponseEntity.ok().body(extraResDto);
  }

  /**
   * 기타 기록 등록
   */
  @PostMapping
  @Operation(summary = "기타 기록을 등록함")
  public ResponseEntity<?> registExtra(@RequestBody ExtraPostDto extraPostDto) {
    ExtraResDto extraResDto = extraService.registExtra(extraPostDto);
    return ResponseEntity.ok().body(extraResDto);
  }

  /**
   * 기타 기록 수정
   */
  @PutMapping
  @Operation(summary = "기타 기록을 수정함")
  public ResponseEntity<?> updateExtra(@RequestBody ExtraPutDto extraPutDto) {
    extraService.updateExtra(extraPutDto);
    return ResponseEntity.ok().build();
  }

  /**
   * 기타 기록 삭제
   */
  @DeleteMapping("/{extraId}")
  @Operation(summary = "기타 기록을 삭제함")
  public ResponseEntity<?> deleteExtra(@PathVariable Long extraId) {
    extraService.deleteExtra(extraId);
    return ResponseEntity.ok().build();
  }
}
