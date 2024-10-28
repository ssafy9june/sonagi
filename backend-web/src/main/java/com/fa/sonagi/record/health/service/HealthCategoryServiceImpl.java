package com.fa.sonagi.record.health.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fa.sonagi.record.health.dto.FeverResDto;
import com.fa.sonagi.record.health.dto.HealthResDto;
import com.fa.sonagi.record.health.entity.Fever;
import com.fa.sonagi.record.health.entity.Hospital;
import com.fa.sonagi.record.health.entity.Medication;
import com.fa.sonagi.record.health.repository.FeverRepository;
import com.fa.sonagi.record.health.repository.HospitalRepository;
import com.fa.sonagi.record.health.repository.MedicationRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HealthCategoryServiceImpl implements HealthCategoryService {
	private final FeverRepository feverRepository;
	private final MedicationRepository medicationRepository;
	private final HospitalRepository hospitalRepository;

	/**
	 * babyId와 createdDate로 모든 체온 데이터 찾기
	 */
	@Override
	public List<FeverResDto> findAllFever(Long babyId, LocalDate createdDate) {
		List<Fever> findFevers = feverRepository.findByBabyIdAndCreatedDateOrderByCreatedTimeAsc(babyId, createdDate);

		List<FeverResDto> fevers = findFevers.stream()
			.map(f -> new FeverResDto(f.getId(), f.getCreatedTime(), f.getBodyTemperature(), f.getMemo()))
			.collect(Collectors.toList());

		return fevers;
	}

	/**
	 * babyId와 createdDate로 모든 투약 데이터 찾기
	 */
	@Override
	public List<HealthResDto> findAllMedication(Long babyId, LocalDate createdDate) {
		List<Medication> findMedicatoins = medicationRepository.findByBabyIdAndCreatedDateOrderByCreatedTimeAsc(babyId,
			createdDate);

		List<HealthResDto> medications = findMedicatoins.stream()
			.map(m -> new HealthResDto(m.getId(), m.getCreatedTime(), m.getMemo()))
			.collect(Collectors.toList());

		return medications;
	}

	/**
	 * babyId와 createdDate로 모든 병원 데이터 찾기
	 */
	@Override
	public List<HealthResDto> findAllHospital(Long babyId, LocalDate createdDate) {
		List<Hospital> findHospitals = hospitalRepository.findByBabyIdAndCreatedDateOrderByCreatedTimeAsc(babyId,
			createdDate);

		List<HealthResDto> hospitals = findHospitals.stream()
			.map(h -> new HealthResDto(h.getId(), h.getCreatedTime(), h.getMemo()))
			.collect(Collectors.toList());

		return hospitals;
	}
}
