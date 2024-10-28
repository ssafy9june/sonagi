package com.fa.sonagi.record.meal.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class AllMealResDto {
	private List<MealResDto> babyFoods;
	private List<MealResDto> breastFeedings;
	private List<FeedingResDto> feedings;
	private List<MealResDto> infantFormulas;
	private List<MealResDto> milks;
	private List<SnackResDto> snacks;

	public AllMealResDto() {
		this.babyFoods = new ArrayList<>();
		this.breastFeedings = new ArrayList<>();
		this.feedings = new ArrayList<>();
		this.infantFormulas = new ArrayList<>();
		this.milks = new ArrayList<>();
		this.snacks = new ArrayList<>();
	}

	public void setAllResDto(List<MealResDto> babyFoods, List<MealResDto> breastFeedings, List<FeedingResDto> feedings,
							List<MealResDto> infantFormulas, List<MealResDto> milks, List<SnackResDto> snacks) {
		this.babyFoods = babyFoods;
		this.breastFeedings = breastFeedings;
		this.feedings = feedings;
		this.infantFormulas = infantFormulas;
		this.milks = milks;
		this.snacks = snacks;
	}
}