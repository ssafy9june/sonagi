package com.fa.sonagi.immunization.service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fa.sonagi.baby.entity.Baby;
import com.fa.sonagi.baby.repository.BabyRepository;
import com.fa.sonagi.immunization.dto.VaccinationPutDto;
import com.fa.sonagi.immunization.dto.VaccinationResDto;
import com.fa.sonagi.immunization.entity.VaccinationStatus;
import com.fa.sonagi.immunization.repository.VaccinationStatusRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class VaccinationServiceImpl implements VaccinationService {
	private final VaccinationStatusRepository vaccinationStatusRepository;
	private final BabyRepository babyRepository;

	/**
	 * 접종 리스트 조회
	 */
	@Override
	public VaccinationResDto findVaccination(Long babyId, Long vaccinationId) {
		Optional<Baby> byId = babyRepository.findById(babyId);
		LocalDate birth = byId.get().getBirthDate();

		VaccinationStatus vaccinationStatus = vaccinationStatusRepository.findByBabyIdAndVaccinationId(babyId,
			vaccinationId);

		return VaccinationResDto.builder()
			.vaccinationStatusId(vaccinationStatus.getId())
			.vaccinationId(vaccinationStatus.getVaccination().getId())
			.disease(vaccinationStatus.getVaccination().getDisease())
			.vaccineName(vaccinationStatus.getVaccination().getVaccineName())
			.startDate(birth.plusDays(vaccinationStatus.getVaccination().getStartDate()))
			.endDate(birth.plusDays(vaccinationStatus.getVaccination().getEndDate()))
			.vaccinationDate(vaccinationStatus.getVaccinationDate())
			.content1(vaccinationStatus.getVaccination().getContent1())
			.title1(vaccinationStatus.getVaccination().getTitle1())
			.content2(vaccinationStatus.getVaccination().getContent2())
			.title2(vaccinationStatus.getVaccination().getTitle2())
			.content3(vaccinationStatus.getVaccination().getContent3())
			.title3(vaccinationStatus.getVaccination().getTitle3())
			.content4(vaccinationStatus.getVaccination().getContent4())
			.title4(vaccinationStatus.getVaccination().getTitle4())
			.content5(vaccinationStatus.getVaccination().getContent5())
			.title5(vaccinationStatus.getVaccination().getTitle5())
			.content6(vaccinationStatus.getVaccination().getContent6())
			.title6(vaccinationStatus.getVaccination().getTitle6())
			.build();

	}

	/**
	 * 접종 상세 정보 조회
	 */
	@Override
	public List<VaccinationResDto> findVaccinationList(Long babyId) {
		Optional<Baby> byId = babyRepository.findById(babyId);
		LocalDate birth = byId.get().getBirthDate();

		List<VaccinationStatus> vaccinationStatus = vaccinationStatusRepository.findByBabyId(babyId);
		List<VaccinationResDto> vaccinationResDto = vaccinationStatus.stream()
			.map(v -> VaccinationResDto.builder()
				.vaccinationStatusId(v.getId())
				.vaccinationId(v.getVaccination().getId())
				.disease(v.getVaccination().getDisease())
				.vaccineName(v.getVaccination().getVaccineName())
				.startDate(birth.plusDays(v.getVaccination().getStartDate()))
				.endDate(birth.plusDays(v.getVaccination().getEndDate()))
				.vaccinationDate(v.getVaccinationDate())
				.content1(v.getVaccination().getContent1())
				.title1(v.getVaccination().getTitle1())
				.content2(v.getVaccination().getContent2())
				.title2(v.getVaccination().getTitle2())
				.content3(v.getVaccination().getContent3())
				.title3(v.getVaccination().getTitle3())
				.content4(v.getVaccination().getContent4())
				.title4(v.getVaccination().getTitle4())
				.content5(v.getVaccination().getContent5())
				.title5(v.getVaccination().getTitle5())
				.content6(v.getVaccination().getContent6())
				.title6(v.getVaccination().getTitle6())
				.build())
			.collect(Collectors.toList());

		Collections.sort(vaccinationResDto, Comparator.comparing(VaccinationResDto::getStartDate));

		return vaccinationResDto;
	}

	/**
	 * 접종일 등록, 수정
	 */
	@Override
	@Transactional
	public void updateVaccinationDate(VaccinationPutDto vaccinationPutDto) {
		VaccinationStatus vaccinationStatus = vaccinationStatusRepository.findById(vaccinationPutDto.getVaccinationStatusId())
			.orElseThrow();
		vaccinationStatus.updateVaccination(vaccinationPutDto.getVaccinationDate());
	}
}
