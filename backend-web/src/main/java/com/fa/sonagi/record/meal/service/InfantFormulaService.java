package com.fa.sonagi.record.meal.service;

import com.fa.sonagi.record.meal.dto.MealPostDto;
import com.fa.sonagi.record.meal.dto.MealPutDto;
import com.fa.sonagi.record.meal.dto.MealResDto;

public interface InfantFormulaService {
	MealResDto findInfantFormulaById(Long id);

	MealResDto registInfantFormula(MealPostDto mealPostDto);

	void updateInfantFormula(MealPutDto mealPutDto);

	void deleteInfantFormulaById(Long id);
}
