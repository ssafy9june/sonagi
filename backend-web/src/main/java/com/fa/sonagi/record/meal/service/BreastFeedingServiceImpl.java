package com.fa.sonagi.record.meal.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fa.sonagi.baby.entity.Baby;
import com.fa.sonagi.baby.repository.BabyRepository;
import com.fa.sonagi.record.meal.dto.MealPostDto;
import com.fa.sonagi.record.meal.dto.MealPutDto;
import com.fa.sonagi.record.meal.dto.MealResDto;
import com.fa.sonagi.record.meal.entity.BreastFeeding;
import com.fa.sonagi.record.meal.repository.BreastFeedingRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BreastFeedingServiceImpl implements BreastFeedingService {

	private final BreastFeedingRepository breastFeedingRepository;
	private final BabyRepository babyRepository;

	/**
	 * 유축 수유 기록 아이디로 조회
	 */
	@Override
	public MealResDto findBreastFeedingById(Long id) {
		return breastFeedingRepository.findBreastFeedingRecord(id);
	}

	/**
	 * 유축 수유 기록 등록
	 */
	@Override
	@Transactional
	public MealResDto registBreastFeeding(MealPostDto mealPostDto) {
		BreastFeeding breastFeeding = BreastFeeding
			.builder()
			.userId(mealPostDto.getUserId())
			.babyId(mealPostDto.getBabyId())
			.createdTime(mealPostDto.getCreatedTime())
			.createdDate(mealPostDto.getCreatedDate())
			.amount(mealPostDto.getAmount())
			.memo(mealPostDto.getMemo())
			.build();

		Baby baby = babyRepository
			.findById(mealPostDto.getBabyId())
			.orElseThrow();
		baby.updateLastMealTime(LocalDateTime.of(mealPostDto.getCreatedDate(), mealPostDto.getCreatedTime()));
		babyRepository.save(baby);

		breastFeedingRepository.save(breastFeeding);
		baby.updateMealOn("N");

		return MealResDto.builder()
			.mealId(breastFeeding.getId())
			.amount(breastFeeding.getAmount())
			.memo(breastFeeding.getMemo())
			.createdTime(breastFeeding.getCreatedTime())
			.build();
	}

	/**
	 * 유축 수유 기록 수정
	 */
	@Override
	@Transactional
	public void updateBreastFeeding(MealPutDto mealPutDto) {
		BreastFeeding breastFeeding = breastFeedingRepository
			.findById(mealPutDto.getMealId())
			.orElseThrow();

		breastFeeding.updateBreastFeeding(mealPutDto.getAmount(), mealPutDto.getMemo(), mealPutDto.getCreatedTime());
	}

	/**
	 * 유축 수유 기록 삭제
	 */
	@Override
	@Transactional
	public void deleteBreastFeedingById(Long id) {
		BreastFeeding breastFeeding = breastFeedingRepository
			.findById(id)
			.orElseThrow();

		breastFeedingRepository.delete(breastFeeding);
	}
}
