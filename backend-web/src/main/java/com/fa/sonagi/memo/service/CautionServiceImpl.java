package com.fa.sonagi.memo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fa.sonagi.memo.dto.MemoPostDto;
import com.fa.sonagi.memo.dto.MemoPutDto;
import com.fa.sonagi.memo.dto.MemoResDto;
import com.fa.sonagi.memo.entity.Caution;
import com.fa.sonagi.memo.repository.CautionRepository;
import com.fa.sonagi.user.entity.Users;
import com.fa.sonagi.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CautionServiceImpl implements CautionService {
	private final CautionRepository cautionRepository;
	private final UserRepository userRepository;

	/**
	 * 주의사항 메모 리스트 아이디로 조회
	 */
	@Override
	public List<MemoResDto> findCautionMemosByBabyId(Long babyId) {
		List<Caution> caution = cautionRepository.findByBabyId(babyId);

		return caution.stream()
			.map(c -> MemoResDto.builder()
				.memoId(c.getId())
				.userId(c.getUser().getUserId())
				.name(c.getUser().getName())
				.memo(c.getMemo())
				.build())
			.collect(Collectors.toList());
	}

	/**
	 * 주의사항 메모 아이디로 조회
	 */
	@Override
	public MemoResDto findCautionById(Long id) {
		Caution caution = cautionRepository.findById(id).orElseThrow();

		return MemoResDto.builder()
			.memoId(caution.getId())
			.userId(caution.getUser().getUserId())
			.name(caution.getUser().getName())
			.memo(caution.getMemo())
			.build();
	}

	/**
	 * 주의사항 메모 아이디로 등록
	 */
	@Override
	@Transactional
	public void registCaution(MemoPostDto memoPostDto) {
		Users user = userRepository.findById(memoPostDto.getUserId()).orElseThrow();
		Caution caution = Caution.builder()
			.babyId(memoPostDto.getBabyId())
			.user(user)
			.memo(memoPostDto.getMemo())
			.build();

		cautionRepository.save(caution);
	}

	/**
	 * 주의사항 메모 아이디로 수정
	 */
	@Override
	@Transactional
	public void updateCaution(MemoPutDto memoPutDto) {
		Caution caution = cautionRepository.findById(memoPutDto.getMemoId()).orElseThrow();
		caution.updateCaution(memoPutDto.getMemo());
	}

	/**
	 * 주의사항 메모 아이디로 삭제
	 */
	@Override
	@Transactional
	public void deleteCaution(Long id) {
		Caution caution = cautionRepository.findById(id).orElseThrow();
		cautionRepository.delete(caution);
	}
}
