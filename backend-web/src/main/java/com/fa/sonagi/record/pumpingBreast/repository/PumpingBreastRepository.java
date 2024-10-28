package com.fa.sonagi.record.pumpingBreast.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fa.sonagi.record.pumpingBreast.entity.PumpingBreast;

public interface PumpingBreastRepository extends JpaRepository<PumpingBreast, Long>, PumpingBreastRepositoryCustom {
	List<PumpingBreast> findByBabyIdAndCreatedDateOrderByCreatedTimeAsc(Long babyId, LocalDate createdDate);
}
