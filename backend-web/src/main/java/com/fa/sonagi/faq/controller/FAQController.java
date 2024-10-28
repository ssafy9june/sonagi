package com.fa.sonagi.faq.controller;

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

import com.fa.sonagi.faq.dto.FAQPostDto;
import com.fa.sonagi.faq.dto.FAQPutDto;
import com.fa.sonagi.faq.dto.FAQResDto;
import com.fa.sonagi.faq.service.FAQService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "FAQ", description = "FAQ API")
@RequestMapping("/api/faqs")
@RestController
@RequiredArgsConstructor
public class FAQController {
	private final FAQService faqService;

	/**
	 * FAQ 기록 전체 조회
	 */
	@GetMapping
	@Operation(summary = "FAQ 기록들을 조회함")
	public List<FAQResDto> getAllFAQ() {
		return faqService.findAllFAQ();
	}

	/**
	 * FAQ 회원정보 카테고리 조회
	 */
	@GetMapping("/member")
	@Operation(summary = "FAQ 회원정보 카테고리를 조회함")
	public List<FAQResDto> getMemberFAQ() {
		return faqService.findMemberFAQ();
	}

	/**
	 * FAQ 운영정책 카테고리 조회
	 */
	@GetMapping("/operation")
	@Operation(summary = "FAQ 운영정책 카테고리를 조회함")
	public List<FAQResDto> getOperationFAQ() {
		return faqService.findOperationFAQ();
	}

	/**
	 * FAQ 이용문의 카테고리 조회
	 */
	@GetMapping("/use")
	@Operation(summary = "FAQ 회원정보 카테고리를 조회함")
	public List<FAQResDto> getUseFAQ() {
		return faqService.findUseFAQ();
	}

	/**
	 * FAQ 기타 카테고리 조회
	 */
	@GetMapping("/etc")
	@Operation(summary = "FAQ 회원정보 카테고리를 조회함")
	public List<FAQResDto> getEtcFAQ() {
		return faqService.findEtcFAQ();
	}

	/**
	 * FAQ 기록 조회
	 */
	@GetMapping("/{FAQId}")
	@Operation(summary = "FAQId로 상세 조회함")
	public ResponseEntity<?> getFAQ(@PathVariable Long FAQId) {
		FAQResDto faqResDto = faqService.findFAQById(FAQId);

		return ResponseEntity.ok().body(faqResDto);
	}

	/**
	 * FAQ 기록 등록
	 */
	@PostMapping
	@Operation(summary = "FAQ 기록들을 등록함")
	public ResponseEntity<?> registFAQ(@RequestBody FAQPostDto faqPostDto) {
		faqService.registFAQ(faqPostDto);
		return ResponseEntity.ok().build();
	}

	/**
	 * FAQ 기록 수정
	 */
	@PutMapping
	@Operation(summary = "FAQ를 수정함")
	public ResponseEntity<?> updateFAQ(@RequestBody FAQPutDto faqPutDto) {
		faqService.updateFAQ(faqPutDto);
		return ResponseEntity.ok().build();
	}

	/**
	 * FAQ 기록 삭제
	 */
	@DeleteMapping("/{FAQId}")
	@Operation(summary = "FAQId로 FAQ를 삭제함")
	public ResponseEntity<?> deleteFAQ(@PathVariable Long FAQId) {
		faqService.deleteFAQ(FAQId);
		return ResponseEntity.ok().build();
	}
}
