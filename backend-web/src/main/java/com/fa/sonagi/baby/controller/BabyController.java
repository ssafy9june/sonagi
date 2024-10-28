package com.fa.sonagi.baby.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fa.sonagi.baby.dto.BabyCodePosDto;
import com.fa.sonagi.baby.dto.BabyCodeResDto;
import com.fa.sonagi.baby.dto.BabyDetailPutDto;
import com.fa.sonagi.baby.dto.BabyDetailResDto;
import com.fa.sonagi.baby.dto.BabyInfoPostDto;
import com.fa.sonagi.baby.dto.BabyInfoResDto;
import com.fa.sonagi.baby.dto.CoparentResDto;
import com.fa.sonagi.baby.service.BabyService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "BabyInfo", description = "아이 정보 API")
@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class BabyController {

	private final BabyService babyService;

	/**
	 * 아이 정보 생성
	 */
	@PostMapping("/babyInfos")
	@Operation(summary = "아이 정보 등록")
	public ResponseEntity<?> registBaby(@RequestBody BabyInfoPostDto babyInfoPostDto) {
		babyService.registBabyInfo(babyInfoPostDto);
		return ResponseEntity.ok().build();
	}

	/**
	 * 아이 코드 조회
	 */
	@GetMapping("/babyCode")
	@Operation(summary = "아이 정보를 등록하기 위한 코드를 가져옴")
	public ResponseEntity<?> getBabyCode(@RequestParam Long babyId) {
		BabyCodeResDto babyCode = babyService.getBabyCode(babyId);
		return ResponseEntity.ok().body(babyCode);
	}

	/**
	 * 아이 코드로 아기 정보 등록
	 */
	@PostMapping("/babyCode")
	@Operation(summary = "아이의 코드로 아이 정보를 등록함")
	public ResponseEntity<?> registBabyByBabyCode(@RequestBody BabyCodePosDto babyCodePosDto) {
		babyService.registUserBabyByCode(babyCodePosDto);
		return ResponseEntity.ok().build();
	}

	/**
	 * 사용자별 아이 리스트(상단바) 조회
	 */
	@GetMapping("/babyInfos/{userId}")
	@Operation(summary = "유저의 아이디로 상단바의 아이 리스트를 조회함")
	public List<BabyInfoResDto> getBabyListByUserId(@PathVariable Long userId) {
		return babyService.findBabyListByUserId(userId);
	}

	/**
	 * 아이 상세 정보 조회
	 */
	@GetMapping("/babyDetail/{babyId}/{userId}")
	@Operation(summary = "유저의 아이디와 아이의 아이디로 아이의 상세 정보를 조회함")
	public ResponseEntity<?> getBaby(@PathVariable Long babyId, @PathVariable Long userId) {
		BabyDetailResDto babyDetailResDto = babyService.findBabyDetail(babyId, userId);
		return ResponseEntity.ok().body(babyDetailResDto);
	}

	/**
	 * 아이 상세 정보 수정
	 */
	@PutMapping("/babyDetail")
	@Operation(summary = "아이의 상세 정보를 수정함")
	public ResponseEntity<?> updateBaby(@RequestBody BabyDetailPutDto babyDetailPutDto) {
		babyService.updateBabyDetail(babyDetailPutDto);
		return ResponseEntity.ok().build();
	}

	/**
	 * 공동 양육자 리스트 조회
	 */
	@GetMapping("/coparents/{userId}/{babyId}")
	@Operation(summary = "아이의 아이디와 유저의 아이디로 자신 빼고 공동 양육자의 정보들을 조회함")
	public List<CoparentResDto> getCoparentList(@PathVariable Long babyId, @PathVariable Long userId) {
		return babyService.findCoparentListByBabyId(babyId, userId);
	}

	/**
	 * 주양육자가 공동 양육자 삭제, 공동양육자가 아이 정보 삭제
	 */
	@DeleteMapping("/coparents/{babyId}/{userId}")
	@Operation(summary = "아이의 아이디와 유저의 아이디로 공동 양육자에게 해당 아이 정보를 삭제함")
	public ResponseEntity<?> deleteCoparent(@PathVariable Long babyId, @PathVariable Long userId) {
		babyService.deleteCoparent(babyId, userId);
		return ResponseEntity.ok().build();
	}

	/**
	 * 아이 정보 삭제
	 */
	@PutMapping("/babyState/{babyId}")
	@Operation(summary = "아이의 삭제 상태 여부를 Y로 설정함")
	public ResponseEntity<?> deleteBabyInfo(@PathVariable Long babyId) {
		babyService.deleteBabyInfo(babyId);
		return ResponseEntity.ok().build();
	}

	/**
	 * 삭제된 아이 데이터 리스트 조회
	 */
	@GetMapping("/isDeleted")
	@Operation(summary = "삭제된 아이 리스트를 조회함")
	public List<BabyDetailResDto> findDeletedBabyInfoList() {
		return babyService.findDeletedBabyInfoList();
	}

	/**
	 * 아이 정보 복원
	 */
	@PutMapping("/isDeleted/{babyId}")
	@Operation(summary = "삭제된 아이의 상태 여부를 N으로 설정함")
	public ResponseEntity<?> restoreBabyInfo(@PathVariable Long babyId) {
		babyService.restoreBabyInfo(babyId);
		return ResponseEntity.ok().build();
	}
}
