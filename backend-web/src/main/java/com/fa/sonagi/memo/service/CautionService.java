package com.fa.sonagi.memo.service;

import java.util.List;

import com.fa.sonagi.memo.dto.MemoPostDto;
import com.fa.sonagi.memo.dto.MemoPutDto;
import com.fa.sonagi.memo.dto.MemoResDto;

public interface CautionService {
	MemoResDto findCautionById(Long id);

	void registCaution(MemoPostDto memoPostDto);

	void updateCaution(MemoPutDto memoPutDto);

	void deleteCaution(Long id);

	List<MemoResDto> findCautionMemosByBabyId(Long babyId);
}
