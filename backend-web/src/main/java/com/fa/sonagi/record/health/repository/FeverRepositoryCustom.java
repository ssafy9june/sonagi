package com.fa.sonagi.record.health.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.fa.sonagi.record.health.dto.FeverResDto;

public interface FeverRepositoryCustom {
	FeverResDto findFeverRecord(Long feverId);

	Double findFeverAvg(Long babyId, LocalDate createdDate);

	Map<LocalDate, Double> findFeverAvg(Long babyId, LocalDate monday, LocalDate sunday);

	Double findFeverAvgByWeek(Long babyId, LocalDate monday, LocalDate sunday);
}
