package com.fa.sonagi.record.health.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.fa.sonagi.record.health.dto.HealthResDto;
import com.fa.sonagi.statistics.common.dto.Times;

public interface MedicationRepositoryCustom {

	HealthResDto findMedicationRecord(Long medicationId);

	List<Times> findMedicationByDay(Long babyId, LocalDate createdDate);

	Long findMedicationCnt(Long babyId, LocalDate createdDate);

	Map<LocalDate, Long> findMedicationCnt(Long babyId, LocalDate monday, LocalDate sunday);

	Long findMedicationCntByWeek(Long babyId, LocalDate monday, LocalDate sunday);
}
