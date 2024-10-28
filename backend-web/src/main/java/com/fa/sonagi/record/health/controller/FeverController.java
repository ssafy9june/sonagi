package com.fa.sonagi.record.health.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fa.sonagi.record.health.dto.FeverPostDto;
import com.fa.sonagi.record.health.dto.FeverPutDto;
import com.fa.sonagi.record.health.dto.FeverResDto;
import com.fa.sonagi.record.health.service.FeverService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Fever", description = "체온 API")
@RequestMapping("/api/fevers")
@RestController
@RequiredArgsConstructor
public class FeverController {

  private final FeverService feverService;

  /**
   * 체온 기록 조회
   */
  @GetMapping("/{feverId}")
  @Operation(summary = "체온 기록에 관한 상세 정보를 조회함")
  public ResponseEntity<?> getFever(@PathVariable Long feverId) {
    FeverResDto feverResDto = feverService.findFeverById(feverId);
    return ResponseEntity.ok().body(feverResDto);
  }

  /**
   * 체온 기록 등록
   */
  @PostMapping
  @Operation(summary = "체온 기록을 등록함")
  public ResponseEntity<?> registFever(@RequestBody FeverPostDto feverPostDto) {
    FeverResDto feverResDto = feverService.registFever(feverPostDto);
    return ResponseEntity.ok().body(feverResDto);
  }

  /**
   * 체온 기록 수정
   */
  @PutMapping
  @Operation(summary = "체온 기록을 수정함")
  public ResponseEntity<?> updateFever(@RequestBody FeverPutDto feverPutDto) {
    feverService.updateFever(feverPutDto);
    return ResponseEntity.ok().build();
  }

  /**
   * 체온 기록 삭제
   */
  @DeleteMapping("/{feverId}")
  @Operation(summary = "체온 기록을 삭제함")
  public ResponseEntity<?> deleteFever(@PathVariable Long feverId) {
    feverService.deleteFeverById(feverId);
    return ResponseEntity.ok().build();
  }
}