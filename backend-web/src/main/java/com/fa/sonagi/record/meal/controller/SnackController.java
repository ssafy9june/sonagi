package com.fa.sonagi.record.meal.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fa.sonagi.record.meal.dto.SnackPostDto;
import com.fa.sonagi.record.meal.dto.SnackPutDto;
import com.fa.sonagi.record.meal.dto.SnackResDto;
import com.fa.sonagi.record.meal.service.SnackService;

import lombok.RequiredArgsConstructor;

@Tag(name = "Snack", description = "간식 API")
@RequestMapping("/api/snacks")
@RestController
@RequiredArgsConstructor
public class SnackController {

  private final SnackService snackService;

  /**
   * 간식 기록 조회
   */
  @GetMapping("/{snackId}")
  @Operation(summary = "간식 기록에 관한 상세 정보를 조회함")
  public ResponseEntity<?> getSnack(@PathVariable Long snackId) {
    SnackResDto snackResDto = snackService.findSnackById(snackId);
    return ResponseEntity.ok().body(snackResDto);
  }

  /**
   * 간식 기록 등록
   */
  @PostMapping
  @Operation(summary = "간식 기록을 등록함")
  public ResponseEntity<?> registSnack(@RequestBody SnackPostDto snackPostDto) {
    SnackResDto snackResDto = snackService.registSnack(snackPostDto);
    return ResponseEntity.ok().body(snackResDto);
  }

  /**
   * 간식 기록 수정
   */
  @PutMapping
  @Operation(summary = "간식 기록을 수정함")
  public ResponseEntity<?> updateSnack(@RequestBody SnackPutDto snackPutDto) {
    snackService.updateSnack(snackPutDto);
    return ResponseEntity.ok().build();
  }

  /**
   * 간식 기록 삭제
   */
  @DeleteMapping("/{snackId}")
  @Operation(summary = "간식 기록을 삭제함")
  public ResponseEntity<?> deleteBabyFood(@PathVariable Long snackId) {
    snackService.deleteSnackById(snackId);
    return ResponseEntity.ok().build();
  }
}
