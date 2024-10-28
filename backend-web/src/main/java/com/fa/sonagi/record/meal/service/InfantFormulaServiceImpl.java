package com.fa.sonagi.record.meal.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fa.sonagi.baby.entity.Baby;
import com.fa.sonagi.baby.repository.BabyRepository;
import com.fa.sonagi.record.meal.dto.MealPostDto;
import com.fa.sonagi.record.meal.dto.MealPutDto;
import com.fa.sonagi.record.meal.dto.MealResDto;
import com.fa.sonagi.record.meal.entity.InfantFormula;
import com.fa.sonagi.record.meal.repository.InfantFormulaRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class InfantFormulaServiceImpl implements InfantFormulaService {

	private final InfantFormulaRepository infantFormulaRepository;
	private final BabyRepository babyRepository;

	/**
	 * 분유 기록 아이디로 조회
	 */
	@Override
	public MealResDto findInfantFormulaById(Long id) {
		return infantFormulaRepository.findInfantFormulaRecord(id);
	}

	/**
	 * 분유 기록 등록
	 */
	@Override
	@Transactional
	public MealResDto registInfantFormula(MealPostDto mealPostDto) {
		InfantFormula infantFormula = InfantFormula
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

		infantFormulaRepository.save(infantFormula);
		baby.updateMealOn("N");

		return MealResDto.builder()
			.mealId(infantFormula.getId())
			.amount(infantFormula.getAmount())
			.memo(infantFormula.getMemo())
			.createdTime(infantFormula.getCreatedTime())
			.build();
	}

	/**
	 * 분유 기록 수정
	 */
	@Override
	@Transactional
	public void updateInfantFormula(MealPutDto mealPutDto) {
		InfantFormula infantFormula = infantFormulaRepository
			.findById(mealPutDto.getMealId())
			.orElseThrow();

		infantFormula.updateInfantFormula(mealPutDto.getAmount(), mealPutDto.getMemo(), mealPutDto.getCreatedTime());
	}

	/**
	 * 분유 기록 삭제
	 */
	@Override
	@Transactional
	public void deleteInfantFormulaById(Long id) {
		InfantFormula infantFormula = infantFormulaRepository
			.findById(id)
			.orElseThrow();

		infantFormulaRepository.delete(infantFormula);
	}
}
