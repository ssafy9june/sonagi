package com.fa.sonagi.memo.service;

import java.util.List;

import com.fa.sonagi.memo.dto.MemoPostDto;
import com.fa.sonagi.memo.dto.MemoPutDto;
import com.fa.sonagi.memo.dto.MemoResDto;

public interface IllnessService {
	MemoResDto findIllnessById(Long id);

	void registIllness(MemoPostDto memoPostDto);

	void updateIllness(MemoPutDto memoPutDto);

	void deleteIllness(Long id);

	List<MemoResDto> findIllnessMemosByBabyId(Long babyId);
}
