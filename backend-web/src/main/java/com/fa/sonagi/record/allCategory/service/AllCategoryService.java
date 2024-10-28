package com.fa.sonagi.record.allCategory.service;

import java.time.LocalDate;

import com.fa.sonagi.record.allCategory.dto.AllCategoryResDto;

public interface AllCategoryService {
	AllCategoryResDto combineCategory(Long babyId, LocalDate createdDate);
}
