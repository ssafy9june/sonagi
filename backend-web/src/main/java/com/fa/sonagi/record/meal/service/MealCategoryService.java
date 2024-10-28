package com.fa.sonagi.record.meal.service;

import java.time.LocalDate;
import java.util.List;

import com.fa.sonagi.record.meal.dto.FeedingResDto;
import com.fa.sonagi.record.meal.dto.MealResDto;
import com.fa.sonagi.record.meal.dto.SnackResDto;

public interface MealCategoryService {
	List<MealResDto> findAllBabyFood(Long babyId, LocalDate createdDate);

	List<MealResDto> findAllBreastFeeding(Long babyId, LocalDate createdDate);

	List<FeedingResDto> findAllFeeding(Long babyId, LocalDate createdDate);

	List<MealResDto> findAllInfantFormula(Long babyId, LocalDate createdDate);

	List<MealResDto> findAllMilk(Long babyId, LocalDate createdDate);

	List<SnackResDto> findAllSnack(Long babyId, LocalDate createdDate);
}