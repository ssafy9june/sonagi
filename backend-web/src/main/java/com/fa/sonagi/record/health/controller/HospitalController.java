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
import com.fa.sonagi.record.health.service.HospitalService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Hospital", description = "병원 API")
@RequestMapping("/api/hospitals")
@RestController
@RequiredArgsConstructor
public class HospitalController {

  private final HospitalService hospitalService;

  /**
   * 병원 기록 조회
   */
  @GetMapping("/{hospitalId}")
  @Operation(summary = "병원 기록에 관한 상세 정보를 조회함")
  public ResponseEntity<?> getHospital(@PathVariable Long hospitalId) {
    HealthResDto healthResDto = hospitalService.findHospitalById(hospitalId);
    return ResponseEntity.ok().body(healthResDto);
  }

  /**
   * 병원 기록 등록
   */
  @PostMapping
  @Operation(summary = "병원 기록을 등록함")
  public ResponseEntity<?> registHospital(@RequestBody HealthPostDto healthPostDto) {
    HealthResDto healthResDto = hospitalService.registHospital(healthPostDto);
    return ResponseEntity.ok().body(healthResDto);
  }

  /**
   * 병원 기록 수정
   */
  @PutMapping
  @Operation(summary = "병원 기록을 수정함")
  public ResponseEntity<?> updateHospital(@RequestBody HealthPutDto hospitalPutDto) {
    hospitalService.updateHospital(hospitalPutDto);
    return ResponseEntity.ok().build();
  }

  /**
   * 병원 기록 삭제
   */
  @DeleteMapping("/{hospitalId}")
  @Operation(summary = "병원 기록을 삭제함")
  public ResponseEntity<?> deleteHospital(@PathVariable Long hospitalId) {
    hospitalService.deleteHospitalById(hospitalId);
    return ResponseEntity.ok().build();
  }
}
