package com.fa.sonagi.record.extra.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fa.sonagi.record.extra.dto.ExtraResDto;
import com.fa.sonagi.record.extra.entity.Extra;
import com.fa.sonagi.record.extra.repository.ExtraRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ExtraCategoryServiceImpl implements ExtraCategoryService {
	private final ExtraRepository extraRepository;

	@Override
	public List<ExtraResDto> findAllExtra(Long babyId, LocalDate createdDate) {
		List<Extra> findExtra = extraRepository.findByBabyIdAndCreatedDateOrderByCreatedTimeAsc(babyId, createdDate);

		List<ExtraResDto> extras = findExtra.stream()
			.map(e -> new ExtraResDto(e.getId(), e.getCreatedTime(), e.getMemo()))
			.collect(Collectors.toList());

		return extras;
	}
}
