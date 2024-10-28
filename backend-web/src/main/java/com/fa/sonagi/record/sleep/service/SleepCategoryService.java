package com.fa.sonagi.record.sleep.service;

import java.time.LocalDate;
import java.util.List;

import com.fa.sonagi.record.sleep.dto.SleepResDto;

public interface SleepCategoryService {
	List<SleepResDto> findAllSleep(Long babyId, LocalDate createdDate);
}
