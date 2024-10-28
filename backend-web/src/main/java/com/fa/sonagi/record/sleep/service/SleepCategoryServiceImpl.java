package com.fa.sonagi.record.sleep.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fa.sonagi.record.sleep.dto.SleepResDto;
import com.fa.sonagi.record.sleep.entity.Sleep;
import com.fa.sonagi.record.sleep.repository.SleepRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SleepCategoryServiceImpl implements SleepCategoryService {
	private final SleepRepository sleepRepository;

	/**
	 * babyId와 createdDate로 모든 수면 데이터 찾기
	 */
	@Override
	public List<SleepResDto> findAllSleep(Long babyId, LocalDate createdDate) {
		List<Sleep> findSleeps = sleepRepository.findByBabyIdAndCreatedDateOrderByCreatedTimeAsc(babyId, createdDate);

		List<SleepResDto> sleeps = findSleeps.stream()
			.map(s -> new SleepResDto(s.getId(), s.getCreatedTime(), s.getEndTime(), s.getMemo()))
			.collect(Collectors.toList());

		return sleeps;
	}
}
