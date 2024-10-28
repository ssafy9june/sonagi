package com.fa.sonagi.record.meal.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fa.sonagi.baby.entity.Baby;
import com.fa.sonagi.baby.repository.BabyRepository;
import com.fa.sonagi.record.meal.dto.MealPostDto;
import com.fa.sonagi.record.meal.dto.MealPutDto;
import com.fa.sonagi.record.meal.dto.MealResDto;
import com.fa.sonagi.record.meal.entity.Milk;
import com.fa.sonagi.record.meal.repository.MilkRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MilkServiceImpl implements MilkService {

	private final MilkRepository milkRepository;
	private final BabyRepository babyRepository;

	/**
	 * 우유 기록 아이디로 조회
	 */
	@Override
	public MealResDto findMilkById(Long id) {
		return milkRepository.findMilkRecord(id);
	}

	/**
	 * 우유 기록 등록
	 */
	@Override
	@Transactional
	public MealResDto registMilk(MealPostDto mealPostDto) {
		Milk milk = Milk
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

		milkRepository.save(milk);
		baby.updateMealOn("N");

		return MealResDto.builder()
			.mealId(milk.getId())
			.amount(milk.getAmount())
			.memo(milk.getMemo())
			.createdTime(milk.getCreatedTime())
			.build();
	}

	/**
	 * 우유 기록 수정
	 */
	@Override
	@Transactional
	public void updateMilk(MealPutDto mealPutDto) {
		Milk milk = milkRepository
			.findById(mealPutDto.getMealId())
			.orElseThrow();

		milk.updateMilk(mealPutDto.getAmount(), mealPutDto.getMemo(), mealPutDto.getCreatedTime());
	}

	/**
	 * 우유 기록 삭제
	 */
	@Override
	@Transactional
	public void deleteMilkById(Long id) {
		Milk milk = milkRepository
			.findById(id)
			.orElseThrow();

		milkRepository.delete(milk);
	}
}
