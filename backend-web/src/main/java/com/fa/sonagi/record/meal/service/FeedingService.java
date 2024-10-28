package com.fa.sonagi.record.meal.service;

import com.fa.sonagi.record.meal.dto.FeedingPostDto;
import com.fa.sonagi.record.meal.dto.FeedingPutDto;
import com.fa.sonagi.record.meal.dto.FeedingResDto;

public interface FeedingService {
	FeedingResDto findFeedingById(Long id);

	FeedingResDto registFeeding(FeedingPostDto feedingPostDto);

	void updateFeeding(FeedingPutDto feedingPutDto);

	void deleteFeedingById(Long id);
}
