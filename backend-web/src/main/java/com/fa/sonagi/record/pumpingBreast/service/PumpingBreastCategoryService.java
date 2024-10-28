package com.fa.sonagi.record.pumpingBreast.service;

import java.time.LocalDate;
import java.util.List;

import com.fa.sonagi.record.pumpingBreast.dto.PumpingBreastResDto;

public interface PumpingBreastCategoryService {
	List<PumpingBreastResDto> findAllPumpingBreast(Long babyId, LocalDate createdDate);
}
