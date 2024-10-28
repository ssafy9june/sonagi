package com.fa.sonagi.record.diaper.service;

import java.time.LocalDate;
import java.util.List;

import com.fa.sonagi.record.diaper.dto.DiaperResDto;

public interface DiaperCategoryService {
	List<DiaperResDto> findAllPee(Long babyId, LocalDate createdDate);

	List<DiaperResDto> findAllPoop(Long babyId, LocalDate createdDate);
}
