package com.fa.sonagi.record.health.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fa.sonagi.record.health.entity.Fever;

public interface FeverRepository extends JpaRepository<Fever, Long>, FeverRepositoryCustom {
	List<Fever> findByBabyIdAndCreatedDateOrderByCreatedTimeAsc(Long babyId, LocalDate createdDate);

}