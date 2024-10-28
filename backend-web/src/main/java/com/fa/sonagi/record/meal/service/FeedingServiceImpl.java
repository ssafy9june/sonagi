package com.fa.sonagi.record.meal.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fa.sonagi.baby.entity.Baby;
import com.fa.sonagi.baby.repository.BabyRepository;
import com.fa.sonagi.record.meal.dto.FeedingPostDto;
import com.fa.sonagi.record.meal.dto.FeedingPutDto;
import com.fa.sonagi.record.meal.dto.FeedingResDto;
import com.fa.sonagi.record.meal.entity.Feeding;
import com.fa.sonagi.record.meal.repository.FeedingRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FeedingServiceImpl implements FeedingService {

	private final FeedingRepository feedingRepository;
	private final BabyRepository babyRepository;

	/**
	 * 수유 기록 아이디로 조회
	 */
	@Override
	public FeedingResDto findFeedingById(Long id) {
		return feedingRepository.findFeedingRecord(id);
	}

	/**
	 * 수유 기록 등록
	 */
	@Override
	@Transactional
	public FeedingResDto registFeeding(FeedingPostDto feedingPostDto) {
		Feeding feeding = Feeding
			.builder()
			.userId(feedingPostDto.getUserId())
			.babyId(feedingPostDto.getBabyId())
			.leftStartTime(feedingPostDto.getLeftStartTime())
			.rightStartTime(feedingPostDto.getRightStartTime())
			.leftEndTime(feedingPostDto.getLeftEndTime())
			.rightEndTime(feedingPostDto.getRightEndTime())
			.createdDate(feedingPostDto.getCreatedDate())
			.createdTime(feedingPostDto.getCreatedTime())
			.memo(feedingPostDto.getMemo())
			.build();

		Baby baby = babyRepository
			.findById(feedingPostDto.getBabyId())
			.orElseThrow();
		baby.updateLastMealTime(LocalDateTime.of(feedingPostDto.getCreatedDate(), feedingPostDto.getCreatedTime()));
		babyRepository.save(baby);

		feedingRepository.save(feeding);
		baby.updateMealOn("N");

		return FeedingResDto.builder()
			.mealId(feeding.getId())
			.leftStartTime(feeding.getLeftStartTime())
			.rightStartTime(feeding.getRightStartTime())
			.leftEndTime(feeding.getLeftEndTime())
			.rightEndTime(feeding.getRightEndTime())
			.memo(feeding.getMemo())
			.build();
	}

	/**
	 * 수유 기록 수정
	 */
	@Override
	@Transactional
	public void updateFeeding(FeedingPutDto feedingPutDto) {
		Feeding feeding = feedingRepository
			.findById(feedingPutDto.getMealId())
			.orElseThrow();

		feeding.updateFeeding(feedingPutDto.getLeftStartTime(), feedingPutDto.getRightStartTime(), feedingPutDto.getLeftEndTime(), feedingPutDto.getRightEndTime(), feedingPutDto.getMemo());
	}

	/**
	 * 수유 기록 삭제
	 */
	@Override
	@Transactional
	public void deleteFeedingById(Long id) {
		Feeding feeding = feedingRepository
			.findById(id)
			.orElseThrow();

		feedingRepository.delete(feeding);
	}
}
