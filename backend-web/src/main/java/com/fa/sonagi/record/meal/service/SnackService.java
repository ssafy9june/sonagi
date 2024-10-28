package com.fa.sonagi.record.meal.service;

import com.fa.sonagi.record.meal.dto.SnackPostDto;
import com.fa.sonagi.record.meal.dto.SnackPutDto;
import com.fa.sonagi.record.meal.dto.SnackResDto;

public interface SnackService {
	SnackResDto findSnackById(Long id);

	SnackResDto registSnack(SnackPostDto snackPostDto);

	void updateSnack(SnackPutDto snackPutDto);

	void deleteSnackById(Long id);
}
