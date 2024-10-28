package com.fa.sonagi.record.health.service;

import java.time.LocalDate;
import java.util.List;

import com.fa.sonagi.record.health.dto.FeverResDto;
import com.fa.sonagi.record.health.dto.HealthResDto;

public interface HealthCategoryService {
	List<FeverResDto> findAllFever(Long babyId, LocalDate createdDate);

	List<HealthResDto> findAllMedication(Long babyId, LocalDate createdDate);

	List<HealthResDto> findAllHospital(Long babyId, LocalDate createdDate);
}
