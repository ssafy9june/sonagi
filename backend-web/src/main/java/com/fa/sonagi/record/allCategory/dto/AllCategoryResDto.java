package com.fa.sonagi.record.allCategory.dto;

import java.util.ArrayList;
import java.util.List;

import com.fa.sonagi.record.activity.dto.ActivityResDto;
import com.fa.sonagi.record.diaper.dto.DiaperResDto;
import com.fa.sonagi.record.extra.dto.ExtraResDto;
import com.fa.sonagi.record.health.dto.FeverResDto;
import com.fa.sonagi.record.health.dto.HealthResDto;
import com.fa.sonagi.record.meal.dto.FeedingResDto;
import com.fa.sonagi.record.meal.dto.MealResDto;
import com.fa.sonagi.record.meal.dto.SnackResDto;
import com.fa.sonagi.record.pumpingBreast.dto.PumpingBreastResDto;
import com.fa.sonagi.record.sleep.dto.SleepResDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AllCategoryResDto {
	private List<ActivityResDto> plays;
	private List<ActivityResDto> tummytimes;
	private List<DiaperResDto> pees;
	private List<DiaperResDto> poops;
	private List<FeverResDto> fevers;
	private List<HealthResDto> medications;
	private List<HealthResDto> hospitals;
	private List<MealResDto> babyFoods;
	private List<MealResDto> breastFeedings;
	private List<FeedingResDto> feedings;
	private List<MealResDto> infantFormulas;
	private List<MealResDto> milks;
	private List<SnackResDto> snacks;
	private List<PumpingBreastResDto> pumpingBreasts;
	private List<SleepResDto> sleeps;
	private List<ExtraResDto> extras;

	public AllCategoryResDto() {
		this.plays = new ArrayList<>();
		this.tummytimes = new ArrayList<>();
		this.pees = new ArrayList<>();
		this.poops = new ArrayList<>();
		this.fevers = new ArrayList<>();
		this.medications = new ArrayList<>();
		this.hospitals = new ArrayList<>();
		this.babyFoods = new ArrayList<>();
		this.breastFeedings = new ArrayList<>();
		this.feedings = new ArrayList<>();
		this.infantFormulas = new ArrayList<>();
		this.milks = new ArrayList<>();
		this.snacks = new ArrayList<>();
		this.pumpingBreasts = new ArrayList<>();
		this.sleeps = new ArrayList<>();
		this.extras = new ArrayList<>();
	}
}
