package com.fa.sonagi.record.pumpingBreast.controller;

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

import com.fa.sonagi.record.pumpingBreast.dto.PumpingBreastPostDto;
import com.fa.sonagi.record.pumpingBreast.dto.PumpingBreastPutDto;
import com.fa.sonagi.record.pumpingBreast.dto.PumpingBreastResDto;
import com.fa.sonagi.record.pumpingBreast.service.PumpingBreastService;

import lombok.RequiredArgsConstructor;

@Tag(name = "PumpingBreast", description = "유축 API")
@RequestMapping("/api/pumpingBreasts")
@RestController
@RequiredArgsConstructor
public class PumpingBreastController {

  private final PumpingBreastService pumpingBreastService;

  /**
   * 유축 기록 조회
   */
  @GetMapping("{pumpingBreastId}")
  @Operation(summary = "유축 기록에 관한 상세 정보를 조회함")
  public ResponseEntity<?> getPumpingBreast(@PathVariable Long pumpingBreastId) {
    PumpingBreastResDto pumpingBreastResDto = pumpingBreastService.findPumpingBreastById(pumpingBreastId);
    return ResponseEntity.ok().body(pumpingBreastResDto);
  }

  /**
   * 유축 기록 등록
   */
  @PostMapping
  @Operation(summary = "유축 기록을 등록함")
  public ResponseEntity<?> registPumpingBreast(@RequestBody PumpingBreastPostDto pumpingBreastPostDto) {
    PumpingBreastResDto pumpingBreastResDto = pumpingBreastService.registPumpingBreast(pumpingBreastPostDto);
    return ResponseEntity.ok().body(pumpingBreastResDto);
  }

  /**
   * 유축 기록 수정
   */
  @PutMapping
  @Operation(summary = "유축 기록을 수정함")
  public ResponseEntity<?> updatePumpingBreast(@RequestBody PumpingBreastPutDto pumpingBreastPutDto) {
    pumpingBreastService.updatePumpingBreast(pumpingBreastPutDto);
    return ResponseEntity.ok().build();
  }

  /**
   * 유축 기록 삭제
   */
  @DeleteMapping("/{pumpingBreastId}")
  @Operation(summary = "유축 기록을 삭제함")
  public ResponseEntity<?> deletePumpingBreast(@PathVariable Long pumpingBreastId) {
    pumpingBreastService.deletePumpingBreastById(pumpingBreastId);
    return ResponseEntity.ok().build();
  }
}
