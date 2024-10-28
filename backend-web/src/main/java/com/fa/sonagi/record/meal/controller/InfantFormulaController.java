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
import com.fa.sonagi.record.meal.service.InfantFormulaService;

import lombok.RequiredArgsConstructor;

@Tag(name = "InfantFormula", description = "분유 API")
@RequestMapping("/api/infantFormulas")
@RestController
@RequiredArgsConstructor
public class InfantFormulaController {

  private final InfantFormulaService infantFormulaService;

  /**
   * 분유 기록 조회
   */
  @GetMapping("/{infantFormulaId}")
  @Operation(summary = "분유 기록에 관한 상세 정보를 조회함")
  public ResponseEntity<?> getInfantFormula(@PathVariable Long infantFormulaId) {
    MealResDto mealResDto = infantFormulaService.findInfantFormulaById(infantFormulaId);
    return ResponseEntity.ok().body(mealResDto);
  }

  /**
   * 분유 기록 등록
   */
  @PostMapping
  @Operation(summary = "분유 기록을 등록함")
  public ResponseEntity<?> registPumpingBreast(@RequestBody MealPostDto mealPostDto) {
    MealResDto mealResDto = infantFormulaService.registInfantFormula(mealPostDto);
    return ResponseEntity.ok().body(mealResDto);
  }

  /**
   * 분유 기록 수정
   */
  @PutMapping
  @Operation(summary = "분유 기록을 수정함")
  public ResponseEntity<?> updatePumpingBreast(@RequestBody MealPutDto mealPutDto) {
    infantFormulaService.updateInfantFormula(mealPutDto);
    return ResponseEntity.ok().build();
  }

  /**
   * 분유 기록 삭제
   */
  @DeleteMapping("/{infantFormulaId}")
  @Operation(summary = "분유 기록을 삭제함")
  public ResponseEntity<?> deletePumpingBreast(@PathVariable Long infantFormulaId) {
    infantFormulaService.deleteInfantFormulaById(infantFormulaId);
    return ResponseEntity.ok().build();
  }
}
