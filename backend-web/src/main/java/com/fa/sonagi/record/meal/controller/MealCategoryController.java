package com.fa.sonagi.record.meal.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fa.sonagi.record.meal.dto.AllMealResDto;
import com.fa.sonagi.record.meal.dto.FeedingResDto;
import com.fa.sonagi.record.meal.dto.MealResDto;
import com.fa.sonagi.record.meal.dto.SnackResDto;
import com.fa.sonagi.record.meal.service.MealCategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Meal", description = "식사 카테고리 api")
@RequestMapping("/api/mealRecords")
@RestController
@RequiredArgsConstructor
public class MealCategoryController {

	private final MealCategoryService mealCategoryService;

	/**
	 * 식사 카테고리 조회
	 */
	@GetMapping
	@Operation(summary = "아이 아이디에 해당하는 아이의 해당 날짜에 관한 식사 카테고리 기록들을 조회함")
	public ResponseEntity<?> getMealRecord(@RequestParam Long babyId, @RequestParam LocalDate createdDate) {
		List<MealResDto> babyFoodList = mealCategoryService.findAllBabyFood(babyId, createdDate);
		List<MealResDto> breastFeedingList = mealCategoryService.findAllBreastFeeding(babyId, createdDate);
		List<FeedingResDto> feedingList = mealCategoryService.findAllFeeding(babyId, createdDate);
		List<MealResDto> infantFormualList = mealCategoryService.findAllInfantFormula(babyId, createdDate);
		List<MealResDto> milkList = mealCategoryService.findAllMilk(babyId, createdDate);
		List<SnackResDto> snackList = mealCategoryService.findAllSnack(babyId, createdDate);

		AllMealResDto allMealResDto = new AllMealResDto();
		allMealResDto.setAllResDto(babyFoodList, breastFeedingList, feedingList, infantFormualList, milkList, snackList);

		return ResponseEntity.ok().body(allMealResDto);
	}
}
