package com.fa.sonagi.record.pumpingBreast.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fa.sonagi.record.pumpingBreast.dto.PumpingBreastResDto;
import com.fa.sonagi.record.pumpingBreast.entity.PumpingBreast;
import com.fa.sonagi.record.pumpingBreast.repository.PumpingBreastRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PumpingBreastCategoryServiceImpl implements PumpingBreastCategoryService {

	private final PumpingBreastRepository pumpingBreastRepository;

	/**
	 * babyId와 createdDate로 모든 유축 데이터 찾기
	 */
	@Override
	public List<PumpingBreastResDto> findAllPumpingBreast(Long babyId, LocalDate createdDate) {
		List<PumpingBreast> findPumpingBreasts = pumpingBreastRepository.findByBabyIdAndCreatedDateOrderByCreatedTimeAsc(
			babyId, createdDate);

		List<PumpingBreastResDto> pumpingBreasts = findPumpingBreasts.stream()
			.map(pb -> new PumpingBreastResDto(pb.getId(), pb.getAmount(), pb.getMemo(), pb.getCreatedTime()))
			.collect(Collectors.toList());

		return pumpingBreasts;
	}
}
