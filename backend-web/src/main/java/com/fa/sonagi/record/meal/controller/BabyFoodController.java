package com.fa.sonagi.record.meal.controller;

import com.fa.sonagi.record.meal.dto.MealPostDto;
import com.fa.sonagi.record.meal.dto.MealPutDto;
import com.fa.sonagi.record.meal.dto.MealResDto;
import com.fa.sonagi.record.meal.service.BabyFoodService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "BabyFood", description = "이유식 API")
@RequestMapping("/api/babyFoods")
@RestController
@RequiredArgsConstructor
public class BabyFoodController {

  private final BabyFoodService babyFoodService;

  /**
   * 이유식 기록 조회
   */
  @GetMapping("/{babyFoodId}")
  @Operation(summary = "이유식 기록에 관한 상세 정보를 조회함")
  public ResponseEntity<?> getBabyFood(@PathVariable Long babyFoodId) {
    MealResDto mealResDto = babyFoodService.findBabyFoodById(babyFoodId);
    return ResponseEntity.ok().body(mealResDto);
  }

  /**
   * 이유식 기록 등록
   */
  @PostMapping
  @Operation(summary = "이유식 기록을 등록함")
  public ResponseEntity<?> registBabyFood(@RequestBody MealPostDto mealPostDto) {
    MealResDto mealResDto = babyFoodService.registBabyFood(mealPostDto);
    return ResponseEntity.ok().body(mealResDto);
  }

  /**
   * 이유식 기록 수정
   */
  @PutMapping
  @Operation(summary = "이유식 기록을 수정함")
  public ResponseEntity<?> updateBabyFood(@RequestBody MealPutDto mealPutDto) {
    babyFoodService.updateBabyFood(mealPutDto);
    return ResponseEntity.ok().build();
  }

  /**
   * 이유식 기록 삭제
   */
  @DeleteMapping("/{babyFoodId}")
  @Operation(summary = "이유식 기록을 삭제함")
  public ResponseEntity<?> deleteBabyFood(@PathVariable Long babyFoodId) {
    babyFoodService.deleteBabyFoodById(babyFoodId);
    return ResponseEntity.ok().build();
  }
}
