package com.fa.sonagi.record.diaper.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.fa.sonagi.record.diaper.dto.DiaperResDto;
import com.fa.sonagi.statistics.common.dto.Times;

public interface PeeRepositoryCustom {

	DiaperResDto findPeeRecord(Long peeId);

	List<Times> findPeeByDay(Long babyId, LocalDate createdDate);

	Long findPeeCnt(Long babyId, LocalDate createdDate);

	Map<LocalDate, List<java.time.LocalTime>> findPeeForWeek(Long babyId, LocalDate monday, LocalDate sunday);

	Long findPeeCntByWeek(Long babyId, LocalDate monday, LocalDate sunday);
}
