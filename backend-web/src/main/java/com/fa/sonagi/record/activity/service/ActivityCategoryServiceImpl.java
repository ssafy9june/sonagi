package com.fa.sonagi.record.activity.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fa.sonagi.record.activity.dto.ActivityResDto;
import com.fa.sonagi.record.activity.entity.Play;
import com.fa.sonagi.record.activity.entity.Tummytime;
import com.fa.sonagi.record.activity.repository.PlayRepository;
import com.fa.sonagi.record.activity.repository.TummytimeRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ActivityCategoryServiceImpl implements ActivityCategoryService {
	private final PlayRepository playRepository;
	private final TummytimeRepository tummytimeRepository;

	/**
	 * babyId와 createdDate로 모든 놀이 데이터 찾기
	 */
	@Override
	public List<ActivityResDto> findAllPlay(Long babyId, LocalDate createdDate) {

		List<Play> findPlays = playRepository.findByBabyIdAndCreatedDateOrderByCreatedTimeAsc(babyId, createdDate);

		List<ActivityResDto> plays = findPlays.stream()
			.map(p -> new ActivityResDto(p.getId(), p.getCreatedTime(), p.getEndTime(), p.getMemo()))
			.collect(Collectors.toList());

		return plays;
	}

	/**
	 * babyId와 createdDate로 터미타임 데이터 찾기
	 */
	@Override
	public List<ActivityResDto> findAllTummytime(Long babyId, LocalDate createdDate) {
		List<Tummytime> findTummytimes = tummytimeRepository.findByBabyIdAndCreatedDateOrderByCreatedTimeAsc(babyId,
			createdDate);

		List<ActivityResDto> tummytimes = findTummytimes.stream()
			.map(t -> new ActivityResDto(t.getId(), t.getCreatedTime(), t.getEndTime(), t.getMemo()))
			.collect(Collectors.toList());

		return tummytimes;
	}
}
