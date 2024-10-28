package com.fa.sonagi.immunization.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fa.sonagi.immunization.dto.VaccinationPutDto;
import com.fa.sonagi.immunization.dto.VaccinationResDto;
import com.fa.sonagi.immunization.service.VaccinationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/vaccination")
@Tag(name = "Vaccination", description = "예방접종 API")
@RestController
@RequiredArgsConstructor
public class VaccinationController {
	private final VaccinationService vaccinationService;

	/**
	 * 예방접종 리스트 조회
	 */
	@GetMapping("/{babyId}")
	@Operation(summary = "아이 아이디로 아이의 예방접종 리스트들을 조회함")
	public List<VaccinationResDto> findVaccinationList(@PathVariable Long babyId) {
		return vaccinationService.findVaccinationList(babyId);
	}

	/**
	 * 예방접종 상세 정보 조회
	 */
	@GetMapping("/{babyId}/{vaccinationId}")
	@Operation(summary = "아이아이디와 예방접종 아이디로 예방접종 상세정보를 조회함")
	public ResponseEntity<?> findVaccination(@PathVariable Long babyId, @PathVariable Long vaccinationId) {
		VaccinationResDto vaccinationResDto = vaccinationService.findVaccination(babyId, vaccinationId);
		return ResponseEntity.ok().body(vaccinationResDto);
	}

	/**
	 * 예방접종일 등록, 수정
	 */
	@PutMapping
	@Operation(summary = "아이의 예방접종일을 수정함")
	public ResponseEntity<?> updateVaccinationDate(@RequestBody VaccinationPutDto vaccinationPutDto) {
		vaccinationService.updateVaccinationDate(vaccinationPutDto);
		return ResponseEntity.ok().build();
	}
}
