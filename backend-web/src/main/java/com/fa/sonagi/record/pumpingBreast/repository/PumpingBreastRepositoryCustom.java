package com.fa.sonagi.record.pumpingBreast.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.fa.sonagi.record.pumpingBreast.dto.PumpingBreastResDto;
import com.fa.sonagi.statistics.pumpingBreast.dto.PumpingBreastStatisticsQueryDto;

public interface PumpingBreastRepositoryCustom {
	PumpingBreastResDto findPumpingBreastRecord(Long pumpingBreastId);

	List<PumpingBreastStatisticsQueryDto> findPumpingBreastByDay(Long babyId, LocalDate createdDate);

	Long findPumpingBreastCnt(Long babyId, LocalDate createdDate);

	Long findPumpingBreastAmount(Long babyId, LocalDate createdDate);

	Map<LocalDate, Long> findPumpingBreastCnt(Long babyId, LocalDate monday, LocalDate sunday);

	Map<LocalDate, Long> findPumpingBreastAmount(Long babyId, LocalDate monday, LocalDate sunday);

	Long findPumpingBreastCntByWeek(Long babyId, LocalDate monday, LocalDate sunday);

	Long findPumpingBreastAmountByWeek(Long babyId, LocalDate monday, LocalDate sunday);
}
