package com.fa.sonagi.record.sleep.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fa.sonagi.record.sleep.dto.SleepPostDto;
import com.fa.sonagi.record.sleep.dto.SleepPutDto;
import com.fa.sonagi.record.sleep.dto.SleepResDto;
import com.fa.sonagi.record.sleep.entity.Sleep;
import com.fa.sonagi.record.sleep.repository.SleepRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SleepServiceImpl implements SleepService {
	private final SleepRepository sleepRepository;

	/**
	 * 수면 기록 아이디로 조회
	 */
	@Override
	public SleepResDto findSleepById(Long id) {
		SleepResDto sleep = sleepRepository.findSleepRecord(id);
		return sleep;
	}

	/**
	 * 수면 기록 등록
	 */
	@Override
	@Transactional
	public SleepResDto registSleep(SleepPostDto sleepPostDto) {
		Sleep sleep = Sleep.builder()
			.userId(sleepPostDto.getUserId())
			.babyId(sleepPostDto.getBabyId())
			.createdDate(sleepPostDto.getCreatedDate())
			.createdTime(sleepPostDto.getCreatedTime())
			.endTime(sleepPostDto.getEndTime())
			.memo(sleepPostDto.getMemo())
			.build();

		sleepRepository.save(sleep);

		return SleepResDto.builder()
			.sleepId(sleep.getId())
			.createdTime(sleep.getCreatedTime())
			.endTime(sleep.getEndTime())
			.memo(sleep.getMemo())
			.build();
	}

	/**
	 * 수면 기록 수정
	 */
	@Override
	@Transactional
	public void updateSleep(SleepPutDto sleepPutDto) {
		Sleep sleep = sleepRepository.findById(sleepPutDto.getSleepId()).orElseThrow();
		sleep.updateSleep(sleepPutDto.getCreatedTime(), sleepPutDto.getEndTime(), sleepPutDto.getMemo());
	}

	/**
	 * 수면 기록 삭제
	 */
	@Override
	@Transactional
	public void deleteSleep(Long id) {
		Sleep sleep = sleepRepository.findById(id).orElseThrow();
		sleepRepository.delete(sleep);
	}
}
