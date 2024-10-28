package com.fa.sonagi.memo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fa.sonagi.memo.dto.MemoPostDto;
import com.fa.sonagi.memo.dto.MemoPutDto;
import com.fa.sonagi.memo.dto.MemoResDto;
import com.fa.sonagi.memo.service.IllnessService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RequestMapping("/api/illness")
@Tag(name = "Illness", description = "질병 API")
@RestController
@RequiredArgsConstructor
public class IllnessController {
	private final IllnessService illnessService;

	/**
	 * 질병 메모 리스트 조회
	 */
	@GetMapping("/{babyId}")
	@Operation(summary = "아이 아이디로 아이의 질병 메모들을 조회함")
	public List<MemoResDto> getIllnessMemosByBabyId(@PathVariable Long babyId) {
		return illnessService.findIllnessMemosByBabyId(babyId);
	}

	/**
	 * 질병 메모 조회
	 */
	@GetMapping("/{babyId}/{illnessId}")
	@Operation(summary = "아이 아이디와 질병 아이디로 상세 조회함")
	public ResponseEntity<?> getIllness(@PathVariable Long illnessId) {
		MemoResDto memoResDto = illnessService.findIllnessById(illnessId);
		return ResponseEntity.ok().body(memoResDto);
	}

	/**
	 * 질병 메모 등록
	 */
	@PostMapping
	@Operation(summary = "질병 메모를 등록함")
	public ResponseEntity<?> registIllness(@RequestBody MemoPostDto memoPostDto) {
		illnessService.registIllness(memoPostDto);
		return ResponseEntity.ok().build();
	}

	/**
	 * 질병 메모 수정
	 */
	@PutMapping
	@Operation(summary = "질병 메모를 수정함")
	public ResponseEntity<?> updateIllness(@RequestBody MemoPutDto memoPutDto) {
		illnessService.updateIllness(memoPutDto);
		return ResponseEntity.ok().build();
	}

	/**
	 * 질병 메모 삭제
	 */
	@DeleteMapping("/{illnessId}")
	@Operation(summary = "질병 메모를 삭제함")
	public ResponseEntity<?> deleteIllness(@PathVariable Long illnessId) {
		illnessService.deleteIllness(illnessId);
		return ResponseEntity.ok().build();
	}
}
