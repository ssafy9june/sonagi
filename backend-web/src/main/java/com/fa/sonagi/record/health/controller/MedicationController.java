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

import com.fa.sonagi.record.health.dto.HealthPostDto;
import com.fa.sonagi.record.health.dto.HealthPutDto;
import com.fa.sonagi.record.health.dto.HealthResDto;
import com.fa.sonagi.record.health.service.MedicationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Medication", description = "투약 API")
@RequestMapping("/api/medications")
@RestController
@RequiredArgsConstructor
public class MedicationController {

  private final MedicationService medicationService;

  /**
   * 투약 기록 등록
   */
  @GetMapping("/{medicationId}")
  @Operation(summary = "투약 기록에 관한 상세 정보를 조회함")
  public ResponseEntity<?> getMedication(@PathVariable Long medicationId) {
    HealthResDto healthResDto = medicationService.findMedicationById(medicationId);
    return ResponseEntity.ok().body(healthResDto);
  }

  /**
   * 투약 기록 등록
   */
  @PostMapping
  @Operation(summary = "투약 기록을 등록함")
  public ResponseEntity<?> registMedication(@RequestBody HealthPostDto healthPostDto) {
    HealthResDto healthResDto = medicationService.registMedication(healthPostDto);
    return ResponseEntity.ok().body(healthResDto);
  }

  /**
   * 투약 기록 수정
   */
  @PutMapping
  @Operation(summary = "투약 기록을 수정함")
  public ResponseEntity<?> updateMedication(@RequestBody HealthPutDto healthPutDto) {
    medicationService.updateMedication(healthPutDto);
    return ResponseEntity.ok().build();
  }

  /**
   * 투약 기록 삭제
   */
  @DeleteMapping("/{medicationId}")
  @Operation(summary = "투약 기록을 삭제함")
  public ResponseEntity<?> deleteMedication(@PathVariable Long medicationId) {
    medicationService.deleteMedicationById(medicationId);
    return ResponseEntity.ok().build();
  }
}