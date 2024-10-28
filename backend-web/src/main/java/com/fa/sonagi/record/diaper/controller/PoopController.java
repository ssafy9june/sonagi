package com.fa.sonagi.record.diaper.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fa.sonagi.record.diaper.dto.DiaperPostDto;
import com.fa.sonagi.record.diaper.dto.DiaperPutDto;
import com.fa.sonagi.record.diaper.dto.DiaperResDto;
import com.fa.sonagi.record.diaper.service.PoopService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Poop", description = "대변 API")
@RequestMapping("/api/poops")
@RestController
@RequiredArgsConstructor
public class PoopController {

  private final PoopService poopService;

  /**
   * 대변 기록 조회
   */
  @GetMapping("/{poopId}")
  @Operation(summary = "대변 기록에 관한 상세 정보를 조회함")
  public ResponseEntity<?> getPoop(@PathVariable Long poopId) {
    DiaperResDto diaperResDto = poopService.findPoopById(poopId);
    return ResponseEntity.ok().body(diaperResDto);
  }

  /**
   * 대변 기록 등록
   */
  @PostMapping
  @Operation(summary = "대변 기록을 등록함")
  public ResponseEntity<?> registPoop(@RequestBody DiaperPostDto diaperPostDto) {
    DiaperResDto diaperResDto = poopService.registPoop(diaperPostDto);
    return ResponseEntity.ok().body(diaperResDto);
  }

  /**
   * 대변 기록 수정
   */
  @PutMapping
  @Operation(summary = "대변 기록을 수정함")
  public ResponseEntity<?> updatePoop(@RequestBody DiaperPutDto diaperPutDto) {
    poopService.updatePoop(diaperPutDto);
    return ResponseEntity.ok().build();
  }

  /**
   * 대변 기록 삭제
   */
  @DeleteMapping("/{poopId}")
  @Operation(summary = "대변 기록을 삭제함")
  public ResponseEntity<?> deletePoop(@PathVariable Long poopId) {
    poopService.deletePoopById(poopId);
    return ResponseEntity.ok().build();
  }
}