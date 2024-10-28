package com.fa.sonagi.record.allCategory.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fa.sonagi.record.activity.dto.ActivityResDto;
import com.fa.sonagi.record.activity.service.ActivityCategoryService;
import com.fa.sonagi.record.allCategory.dto.AllCategoryResDto;
import com.fa.sonagi.record.diaper.dto.DiaperResDto;
import com.fa.sonagi.record.diaper.service.DiaperCategoryService;
import com.fa.sonagi.record.extra.dto.ExtraResDto;
import com.fa.sonagi.record.extra.service.ExtraCategoryService;
import com.fa.sonagi.record.health.dto.FeverResDto;
import com.fa.sonagi.record.health.dto.HealthResDto;
import com.fa.sonagi.record.health.service.HealthCategoryService;
import com.fa.sonagi.record.meal.dto.FeedingResDto;
import com.fa.sonagi.record.meal.dto.MealResDto;
import com.fa.sonagi.record.meal.dto.SnackResDto;
import com.fa.sonagi.record.meal.service.MealCategoryService;
import com.fa.sonagi.record.pumpingBreast.dto.PumpingBreastResDto;
import com.fa.sonagi.record.pumpingBreast.service.PumpingBreastCategoryService;
import com.fa.sonagi.record.sleep.dto.SleepResDto;
import com.fa.sonagi.record.sleep.service.SleepCategoryService;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AllCategoryServiceImpl implements AllCategoryService{
	private final ActivityCategoryService activityCategoryService;
	private final DiaperCategoryService diaperCategoryService;
	private final HealthCategoryService healthCategoryService;
	private final MealCategoryService mealCategoryService;
	private final PumpingBreastCategoryService pumpingBreastCategoryService;
	private final SleepCategoryService sleepCategoryService;
	private final ExtraCategoryService extraCategoryService;

	/**
	 * 모든 기록들을 찾아 한 객체 모아서 보내기
	 */
	@Override
	public AllCategoryResDto combineCategory(Long babyId, LocalDate createdDate) {
		AllCategoryResDto allCategoryResDto = new AllCategoryResDto();

		List<ActivityResDto> plays = activityCategoryService.findAllPlay(babyId, createdDate);
		allCategoryResDto.setPlays(plays);

		List<ActivityResDto> tummyTimes = activityCategoryService.findAllTummytime(babyId, createdDate);
		allCategoryResDto.setTummytimes(tummyTimes);

		List<DiaperResDto> pees = diaperCategoryService.findAllPee(babyId, createdDate);
		allCategoryResDto.setPees(pees);

		List<DiaperResDto> poops = diaperCategoryService.findAllPoop(babyId, createdDate);
		allCategoryResDto.setPoops(poops);

		List<FeverResDto> fevers = healthCategoryService.findAllFever(babyId, createdDate);
		allCategoryResDto.setFevers(fevers);

		List<HealthResDto> medications = healthCategoryService.findAllMedication(babyId, createdDate);
		allCategoryResDto.setMedications(medications);

		List<HealthResDto> hospitals = healthCategoryService.findAllHospital(babyId, createdDate);
		allCategoryResDto.setHospitals(hospitals);

		List<MealResDto> babyFoods = mealCategoryService.findAllBabyFood(babyId, createdDate);
		allCategoryResDto.setBabyFoods(babyFoods);

		List<MealResDto> breastFeedings = mealCategoryService.findAllBreastFeeding(babyId, createdDate);
		allCategoryResDto.setBreastFeedings(breastFeedings);

		List<FeedingResDto> feedings = mealCategoryService.findAllFeeding(babyId, createdDate);
		allCategoryResDto.setFeedings(feedings);

		List<MealResDto> infantFormulas = mealCategoryService.findAllInfantFormula(babyId, createdDate);
		allCategoryResDto.setInfantFormulas(infantFormulas);

		List<MealResDto> milks = mealCategoryService.findAllMilk(babyId, createdDate);
		allCategoryResDto.setMilks(milks);

		List<SnackResDto> snacks = mealCategoryService.findAllSnack(babyId, createdDate);
		allCategoryResDto.setSnacks(snacks);

		List<PumpingBreastResDto> pumpingBreasts = pumpingBreastCategoryService.findAllPumpingBreast(babyId, createdDate);
		allCategoryResDto.setPumpingBreasts(pumpingBreasts);

		List<SleepResDto> sleeps = sleepCategoryService.findAllSleep(babyId, createdDate);
		allCategoryResDto.setSleeps(sleeps);

		List<ExtraResDto> extras = extraCategoryService.findAllExtra(babyId, createdDate);
		allCategoryResDto.setExtras(extras);

		return allCategoryResDto;
	}
}
