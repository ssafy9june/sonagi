package com.fa.sonagi.immunization.service;

import java.util.List;

import com.fa.sonagi.immunization.dto.VaccinationPutDto;
import com.fa.sonagi.immunization.dto.VaccinationResDto;

public interface VaccinationService {
	List<VaccinationResDto> findVaccinationList(Long babyId);

	void updateVaccinationDate(VaccinationPutDto vaccinationPutDto);

	VaccinationResDto findVaccination(Long babyId, Long vaccinationId);
}
