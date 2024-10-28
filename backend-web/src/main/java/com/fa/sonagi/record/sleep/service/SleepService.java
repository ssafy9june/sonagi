package com.fa.sonagi.record.sleep.service;

import com.fa.sonagi.record.sleep.dto.SleepPostDto;
import com.fa.sonagi.record.sleep.dto.SleepPutDto;
import com.fa.sonagi.record.sleep.dto.SleepResDto;

public interface SleepService {
	SleepResDto findSleepById(Long id);

	SleepResDto registSleep(SleepPostDto sleepPostDto);

	void updateSleep(SleepPutDto sleepPutDto);

	void deleteSleep(Long id);
}
