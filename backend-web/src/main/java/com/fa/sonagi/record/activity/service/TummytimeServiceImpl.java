package com.fa.sonagi.record.activity.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fa.sonagi.record.activity.dto.ActivityPostDto;
import com.fa.sonagi.record.activity.dto.ActivityPutDto;
import com.fa.sonagi.record.activity.dto.ActivityResDto;
import com.fa.sonagi.record.activity.entity.Tummytime;
import com.fa.sonagi.record.activity.repository.TummytimeRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TummytimeServiceImpl implements TummytimeService {

	private final TummytimeRepository tummytimeRepository;

	/**
	 * 터미타임 기록 아이디로 조회
	 */

	@Override
	public ActivityResDto findTummytimeById(Long id) {
		ActivityResDto tummytime = tummytimeRepository.findTummytimeRecord(id);

		return tummytime;
	}

	/**
	 * 터미타임 기록 등록
	 */
	@Override
	@Transactional
	public ActivityResDto registTummytime(ActivityPostDto activityPostDto) {
		Tummytime tummytime = Tummytime
			.builder()
			.userId(activityPostDto.getUserId())
			.babyId(activityPostDto.getBabyId())
			.createdDate(activityPostDto.getCreatedDate())
			.createdTime(activityPostDto.getCreatedTime())
			.endTime(activityPostDto.getEndTime())
			.memo(activityPostDto.getMemo())
			.build();

		tummytimeRepository.save(tummytime);

		return ActivityResDto.builder()
			.activityId(tummytime.getId())
			.createdTime(tummytime.getCreatedTime())
			.endTime(tummytime.getEndTime())
			.memo(tummytime.getMemo())
			.build();
	}

	/**
	 * 터미타임 기록 수정
	 */
	@Override
	@Transactional
	public void updateTummytime(ActivityPutDto activityPutDto) {
		Tummytime tummytime = tummytimeRepository
			.findById(activityPutDto.getActivityId())
			.orElseThrow();
		tummytime.updateTummytime(activityPutDto.getCreatedTime(), activityPutDto.getEndTime(),
			activityPutDto.getMemo());
	}

	/**
	 * 터미타임 기록 삭제
	 */
	@Override
	@Transactional
	public void deleteTummytime(Long id) {
		Tummytime tummytime = tummytimeRepository
			.findById(id)
			.orElseThrow();
		tummytimeRepository.delete(tummytime);
	}
}
