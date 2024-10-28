package com.fa.sonagi.record.diaper.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fa.sonagi.record.diaper.dto.DiaperResDto;
import com.fa.sonagi.record.diaper.entity.Pee;
import com.fa.sonagi.record.diaper.entity.Poop;
import com.fa.sonagi.record.diaper.repository.PeeRepository;
import com.fa.sonagi.record.diaper.repository.PoopRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DiaperCategoryServiceImpl implements DiaperCategoryService {
	private final PeeRepository peeRepository;
	private final PoopRepository poopRepository;

	/**
	 * babyId와 createdDate로 모든 소변 데이터 찾기
	 */
	@Override
	public List<DiaperResDto> findAllPee(Long babyId, LocalDate createdDate) {
		List<Pee> findPees = peeRepository.findByBabyIdAndCreatedDateOrderByCreatedTimeAsc(babyId, createdDate);

		List<DiaperResDto> pees = findPees.stream()
			.map(d -> new DiaperResDto(d.getId(), d.getCreatedTime(), d.getMemo()))
			.collect(Collectors.toList());

		return pees;
	}

	/**
	 * babyId와 createdDate로 모든 대변 데이터 찾기
	 */
	@Override
	public List<DiaperResDto> findAllPoop(Long babyId, LocalDate createdDate) {
		List<Poop> findPoops = poopRepository.findByBabyIdAndCreatedDateOrderByCreatedTimeAsc(babyId, createdDate);

		List<DiaperResDto> poops = findPoops.stream()
			.map(d -> new DiaperResDto(d.getId(), d.getCreatedTime(), d.getMemo()))
			.collect(Collectors.toList());

		return poops;
	}

}
