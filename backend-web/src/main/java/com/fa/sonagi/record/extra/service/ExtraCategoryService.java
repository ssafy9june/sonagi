package com.fa.sonagi.record.extra.service;

import java.time.LocalDate;
import java.util.List;

import com.fa.sonagi.record.extra.dto.ExtraResDto;

public interface ExtraCategoryService {
	List<ExtraResDto> findAllExtra(Long babyId, LocalDate createdDate);
}
