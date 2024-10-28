package com.fa.sonagi.record.health.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fa.sonagi.record.health.entity.Medication;

public interface MedicationRepository extends JpaRepository<Medication, Long>, MedicationRepositoryCustom {
	List<Medication> findByBabyIdAndCreatedDateOrderByCreatedTimeAsc(Long babyId, LocalDate createdDate);
}