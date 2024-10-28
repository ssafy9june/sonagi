package com.fa.sonagi.immunization.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fa.sonagi.immunization.entity.Checkup;

public interface CheckupRepository extends JpaRepository<Checkup, Long> {
}
