package com.fa.sonagi.immunization.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fa.sonagi.immunization.entity.Vaccination;

public interface VaccinationRepository extends JpaRepository<Vaccination, Long> {
}
