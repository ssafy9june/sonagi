package com.fa.sonagi.record.activity.service;

import java.time.LocalDate;
import java.util.List;

import com.fa.sonagi.record.activity.dto.ActivityResDto;

public interface ActivityCategoryService {
	List<ActivityResDto> findAllPlay(Long babyId, LocalDate createdDate);

	List<ActivityResDto> findAllTummytime(Long babyId, LocalDate createdDate);
}
