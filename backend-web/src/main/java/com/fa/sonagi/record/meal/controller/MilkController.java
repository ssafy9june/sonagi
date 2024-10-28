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

import com.fa.sonagi.record.meal.dto.MealPostDto;
import com.fa.sonagi.record.meal.dto.MealPutDto;
import com.fa.sonagi.record.meal.dto.MealResDto;
import com.fa.sonagi.record.meal.service.MilkService;

import lombok.RequiredArgsConstructor;

@Tag(name = "Milk", description = "우유 API")
@RequestMapping("/api/milks")
@RestController
@RequiredArgsConstructor
public class MilkController {

  private final MilkService milkService;

  /**
   * 우유 기록 조회
   */
  @GetMapping("/{milkId}")
  @Operation(summary = "우유 기록에 관한 상세 정보를 조회함")
  public ResponseEntity<?> getMilk(@PathVariable Long milkId) {
    MealResDto mealResDto = milkService.findMilkById(milkId);
    return ResponseEntity.ok().body(mealResDto);
  }

  /**
   * 우유 기록 등록
   */
  @PostMapping
  @Operation(summary = "우유 기록을 등록함")
  public ResponseEntity<?> registBabyFood(@RequestBody MealPostDto mealPostDto) {
    MealResDto mealResDto = milkService.registMilk(mealPostDto);
    return ResponseEntity.ok().body(mealResDto);
  }

  /**
   * 우유 기록 수정
   */
  @PutMapping
  @Operation(summary = "우유 기록을 수정함")
  public ResponseEntity<?> updateBabyFood(@RequestBody MealPutDto mealPutDto) {
    milkService.updateMilk(mealPutDto);
    return ResponseEntity.ok().build();
  }

  /**
   * 우유 기록 삭제
   */
  @DeleteMapping("/{milkId}")
  @Operation(summary = "우유 기록을 삭제함")
  public ResponseEntity<?> deleteBabyFood(@PathVariable Long milkId) {
    milkService.deleteMilkById(milkId);
    return ResponseEntity.ok().build();
  }
}
