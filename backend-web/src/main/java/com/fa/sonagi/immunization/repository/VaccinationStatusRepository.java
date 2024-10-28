package com.fa.sonagi.immunization.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fa.sonagi.immunization.entity.VaccinationStatus;

public interface VaccinationStatusRepository extends JpaRepository<VaccinationStatus, Long> {
	List<VaccinationStatus> findByBabyId(Long babyId);

	VaccinationStatus findByBabyIdAndVaccinationId(Long babyId, Long vaccinationId);
}