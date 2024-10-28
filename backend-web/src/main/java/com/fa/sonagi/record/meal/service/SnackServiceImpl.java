package com.fa.sonagi.record.meal.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fa.sonagi.baby.entity.Baby;
import com.fa.sonagi.baby.repository.BabyRepository;
import com.fa.sonagi.record.meal.dto.SnackPostDto;
import com.fa.sonagi.record.meal.dto.SnackPutDto;
import com.fa.sonagi.record.meal.dto.SnackResDto;
import com.fa.sonagi.record.meal.entity.Snack;
import com.fa.sonagi.record.meal.repository.SnackRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SnackServiceImpl implements SnackService {

	private final SnackRepository snackRepository;
	private final BabyRepository babyRepository;

	/**
	 * 간식 기록 아이디로 조회
	 */
	@Override
	public SnackResDto findSnackById(Long id) {
		return snackRepository.findSnackRecord(id);
	}

	/**
	 * 간식 기록 등록
	 */
	@Override
	@Transactional
	public SnackResDto registSnack(SnackPostDto snackPostDto) {
		Snack snack = Snack
			.builder()
			.userId(snackPostDto.getUserId())
			.babyId(snackPostDto.getBabyId())
			.createdTime(snackPostDto.getCreatedTime())
			.createdDate(snackPostDto.getCreatedDate())
			.memo(snackPostDto.getMemo())
			.build();

		Baby baby = babyRepository
			.findById(snackPostDto.getBabyId())
			.orElseThrow();
		baby.updateLastMealTime(LocalDateTime.of(snackPostDto.getCreatedDate(), snackPostDto.getCreatedTime()));
		babyRepository.save(baby);

		snackRepository.save(snack);
		baby.updateMealOn("N");

		return SnackResDto.builder()
			.mealId(snack.getId())
			.memo(snack.getMemo())
			.createdTime(snack.getCreatedTime())
			.build();
	}

	/**
	 * 간식 기록 수정
	 */
	@Override
	@Transactional
	public void updateSnack(SnackPutDto snackPutDto) {
		Snack snack = snackRepository
			.findById(snackPutDto.getMealId())
			.orElseThrow();

		snack.updateSnack(snackPutDto.getMemo(), snackPutDto.getCreatedTime());
	}

	/**
	 * 간식 기록 삭제
	 */
	@Override
	@Transactional
	public void deleteSnackById(Long id) {
		Snack snack = snackRepository
			.findById(id)
			.orElseThrow();

		snackRepository.delete(snack);
	}
}
