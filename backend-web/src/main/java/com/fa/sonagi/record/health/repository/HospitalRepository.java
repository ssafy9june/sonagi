package com.fa.sonagi.record.health.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fa.sonagi.record.health.entity.Hospital;

public interface HospitalRepository extends JpaRepository<Hospital, Long>, HospitalRepositoryCustom {
	List<Hospital> findByBabyIdAndCreatedDateOrderByCreatedTimeAsc(Long babyId, LocalDate createdDate);
}
