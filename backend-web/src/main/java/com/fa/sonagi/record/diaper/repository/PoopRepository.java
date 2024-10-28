package com.fa.sonagi.record.diaper.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fa.sonagi.record.diaper.entity.Poop;

public interface PoopRepository extends JpaRepository<Poop, Long>, PoopRepositoryCustom {
	List<Poop> findByBabyIdAndCreatedDateOrderByCreatedTimeAsc(Long babyId, LocalDate createdDate);
}