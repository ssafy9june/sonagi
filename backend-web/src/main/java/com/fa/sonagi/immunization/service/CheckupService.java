package com.fa.sonagi.immunization.service;

import java.util.List;

import com.fa.sonagi.immunization.dto.CheckupPutDto;
import com.fa.sonagi.immunization.dto.CheckupResDto;

public interface CheckupService {
	List<CheckupResDto> findCheckupList(Long babyId);

	void updateCheckupDate(CheckupPutDto checkupPutDto);

	CheckupResDto findCheckup(Long babyId, Long checkupId);

}
