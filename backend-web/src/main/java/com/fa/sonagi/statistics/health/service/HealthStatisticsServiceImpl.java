package com.fa.sonagi.statistics.health.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fa.sonagi.record.health.repository.FeverRepository;
import com.fa.sonagi.record.health.repository.HospitalRepository;
import com.fa.sonagi.record.health.repository.MedicationRepository;
import com.fa.sonagi.statistics.common.dto.Times;
import com.fa.sonagi.statistics.health.dto.HealthStatisticsDayForWeekDto;
import com.fa.sonagi.statistics.health.dto.HealthStatisticsResDto;
import com.fa.sonagi.statistics.health.dto.HealthStatisticsWeekResDto;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HealthStatisticsServiceImpl implements HealthStatisticsService{

	private final HospitalRepository hospitalRepository;
	private final MedicationRepository medicationRepository;
	private final FeverRepository feverRepository;
	private final int WEEK = 7;

	/**
	 * 일별 통계 계산
	 */
	@Override
	public HealthStatisticsResDto getHealthStatisticsDay(Long babyId, LocalDate createdDate) {
		HealthStatisticsResDto healthStatisticsResDto = new HealthStatisticsResDto();

		List<Times> healthDay = new ArrayList<>();
		// 카테고리별 데이터 조회
		List<Times> hospitals = hospitalRepository.findHospitalByDay(babyId, createdDate);
		for (Times t : hospitals)
			healthDay.add(t);
		List<Times> medications = medicationRepository.findMedicationByDay(babyId, createdDate);
		for (Times t : medications)
			healthDay.add(t);
		Collections.sort(healthDay, Comparator.comparing(Times::getCreatedTime));
		healthStatisticsResDto.setTimes(healthDay);

		// 카테고리별 카드 통계 조회
		Long hospitalCnt = (long)hospitals.size();
		healthStatisticsResDto.setHospitalCnt(hospitalCnt);
		Long medicationCnt = (long)medications.size();
		healthStatisticsResDto.setMedicationCnt(medicationCnt);
		Double feverAvg = feverRepository.findFeverAvg(babyId, createdDate);
		healthStatisticsResDto.setFeverAvg(feverAvg);

		// 하루 전 데이터 조회
		createdDate = createdDate.minus(1, ChronoUnit.DAYS);

		// 병원 횟수 통계 퍼센트 계산
		Long yesterdayHospitalCnt = hospitalRepository.findHospitalCnt(babyId, createdDate);
		Long hospitalCntPercent = getPercent(hospitalCnt, yesterdayHospitalCnt);
		Long yesterdayHospitalCntPercent = getPercent(yesterdayHospitalCnt, hospitalCnt);
		healthStatisticsResDto.setHospitalCntPercent(hospitalCntPercent);
		healthStatisticsResDto.setYesterdayHospitalCntPercent(yesterdayHospitalCntPercent);

		// 투약 횟수 통계 퍼센트 계산
		Long yesterdayMedicationCnt = medicationRepository.findMedicationCnt(babyId, createdDate);
		Long medicationCntPercent = getPercent(medicationCnt, yesterdayMedicationCnt);
		Long yesterdayMedicationCntPercent = getPercent(yesterdayMedicationCnt, medicationCnt);
		healthStatisticsResDto.setMedicationCntPercent(medicationCntPercent);
		healthStatisticsResDto.setYesterdayMedicationCntPercent(yesterdayMedicationCntPercent);

		// 체온 평균 통계 퍼센트 계산
		Double yesterdayFeverAvg = feverRepository.findFeverAvg(babyId, createdDate);
		Long feverPercent = getPercent(feverAvg, yesterdayFeverAvg);
		Long yesterdayFeverPercent = getPercent(yesterdayFeverAvg, feverAvg);
		healthStatisticsResDto.setFeverAvgPercent(feverPercent);
		healthStatisticsResDto.setYesterdayFeverAvgPercent(yesterdayFeverPercent);

		return healthStatisticsResDto;
	}

	/**
	 * 주별 통계 계산
	 */
	@Override
	public HealthStatisticsWeekResDto getHealthStatisticsWeek(Long babyId, LocalDate createdDate) {
		LocalDate monday = createdDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
		LocalDate sunday = createdDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
		HealthStatisticsWeekResDto healthWeek = new HealthStatisticsWeekResDto();

		// 카테고리별 일주일 데이터 조회
		Map<LocalDate, Long> hospitals = hospitalRepository.findHospitalCnt(babyId, monday, sunday);
		Map<LocalDate, Long> medications = medicationRepository.findMedicationCnt(babyId, monday, sunday);
		Map<LocalDate, Double> fevers = feverRepository.findFeverAvg(babyId, monday, sunday);

		// 날짜별 데이터 세팅
		LocalDate writeDay = monday;
		for (int i = 0; i < WEEK; i++) {
			HealthStatisticsDayForWeekDto healthDay = new HealthStatisticsDayForWeekDto();

			if (hospitals.containsKey(writeDay))
				healthDay.setHospitalCnt(hospitals.get(writeDay));
			else
				healthDay.setHospitalCnt(0L);

			if (medications.containsKey(writeDay))
				healthDay.setMedicationCnt(medications.get(writeDay));
			else
				healthDay.setMedicationCnt(0L);

			if (fevers.containsKey(writeDay))
				healthDay.setFeverAvg(fevers.get(writeDay));
			else
				healthDay.setFeverAvg((double)0);

			healthWeek.getHealthStatistics().put(writeDay.format(DateTimeFormatter.ofPattern("M/dd")), healthDay);
			writeDay = writeDay.plusDays(1);
		}

		// 카테고리별 일주일 통계 조회
		Long hospitalCnt = getStatisticsWeek(monday, hospitals);
		healthWeek.setHospitalCnt(hospitalCnt);
		Long medicationCnt = getStatisticsWeek(monday, medications);
		healthWeek.setMedicationCnt(medicationCnt);
		Double feverAvg = feverRepository.findFeverAvgByWeek(babyId, monday, sunday);
		healthWeek.setFeverAvg(feverAvg);

		monday = monday.minusWeeks(1);
		sunday = sunday.minusWeeks(1);

		// 병원 횟수 통계 퍼센트 계산
		Long lastWeekHospitalCnt = hospitalRepository.findHospitalCntByWeek(babyId, monday, sunday);
		Long hospitalCntPercent = getPercent(hospitalCnt, lastWeekHospitalCnt);
		Long lastWeekHospitalCntPercent = getPercent(lastWeekHospitalCnt, hospitalCnt);
		healthWeek.setHospitalCntPercent(hospitalCntPercent);
		healthWeek.setYesterdayHospitalCntPercent(lastWeekHospitalCntPercent);

		// 투약 횟수 통계 퍼센트 계산
		Long lastWeekMedicationCnt = medicationRepository.findMedicationCntByWeek(babyId, monday, sunday);
		Long medicationCntPercent = getPercent(medicationCnt, lastWeekMedicationCnt);
		Long lastWeekMedicationCntPercent = getPercent(lastWeekMedicationCnt, medicationCnt);
		healthWeek.setMedicationCntPercent(medicationCntPercent);
		healthWeek.setYesterdayMedicationCntPercent(lastWeekMedicationCntPercent);

		// 체온 평균 통계 퍼센트 계산
		Double lastWeekFeverAvg = feverRepository.findFeverAvgByWeek(babyId, monday, sunday);
		Long feverPercent = getPercent(feverAvg, lastWeekFeverAvg);
		Long lastWeekFeverPercent = getPercent(lastWeekFeverAvg, feverAvg);
		healthWeek.setFeverAvgPercent(feverPercent);
		healthWeek.setYesterdayFeverAvgPercent(lastWeekFeverPercent);

		return healthWeek;
	}

	/**
	 * 퍼센트 계산하기 Long
	 */
	public Long getPercent(Long target, Long opponent) {
		if (target == 0) return 0L;
		else if (target >= opponent) {
			return 100L;
		}
		else {
			return target * 100 / opponent;
		}
	}

	/**
	 * 퍼센트 계산하기 Double
	 */
	public Long getPercent(Double target, Double opponent) {
		if (target == 0) return 0L;
		else if (target >= opponent) {
			return 100L;
		}
		else {
			return (long)(target * 100 / opponent);
		}
	}

	/**
	 * Long 타입의 일주일 통계 계산하기
	 */
	public Long getStatisticsWeek(LocalDate day, Map<LocalDate, Long> data) {
		Long cnt = 0L;
		for (int i = 0; i < WEEK; i++) {
			cnt += data.getOrDefault(day, 0L);
			day = day.plusDays(1);
		}

		return cnt;
	}
}
