package com.fa.sonagi.record.meal.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fa.sonagi.baby.entity.Baby;
import com.fa.sonagi.baby.repository.BabyRepository;
import com.fa.sonagi.record.meal.dto.MealPostDto;
import com.fa.sonagi.record.meal.dto.MealPutDto;
import com.fa.sonagi.record.meal.dto.MealResDto;
import com.fa.sonagi.record.meal.entity.BabyFood;
import com.fa.sonagi.record.meal.repository.BabyFoodRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BabyFoodServiceImpl implements BabyFoodService {

	private final BabyFoodRepository babyFoodRepository;
	private final BabyRepository babyRepository;

	/**
	 * 이유식 기록 아이디로 조회
	 */
	@Override
	public MealResDto findBabyFoodById(Long id) {
		return babyFoodRepository.findBabyFoodRecord(id);
	}

	/**
	 * 이유식 기록 등록
	 */
	@Override
	@Transactional
	public MealResDto registBabyFood(MealPostDto mealPostDto) {
		BabyFood babyFood = BabyFood
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

		babyFoodRepository.save(babyFood);
		baby.updateMealOn("N");

		return MealResDto.builder()
			.mealId(babyFood.getId())
			.amount(babyFood.getAmount())
			.memo(babyFood.getMemo())
			.createdTime(babyFood.getCreatedTime())
			.build();
	}

	/**
	 * 이유식 기록 수정
	 */
	@Override
	@Transactional
	public void updateBabyFood(MealPutDto mealPutDto) {
		BabyFood babyFood = babyFoodRepository
			.findById(mealPutDto.getMealId())
			.orElseThrow();

		babyFood.updateBabyFood(mealPutDto.getAmount(), mealPutDto.getMemo(), mealPutDto.getCreatedTime());
	}

	/**
	 * 이유식 기록 삭제
	 */
	@Override
	@Transactional
	public void deleteBabyFoodById(Long id) {
		BabyFood babyFood = babyFoodRepository
			.findById(id)
			.orElseThrow();

		babyFoodRepository.delete(babyFood);
	}
}
