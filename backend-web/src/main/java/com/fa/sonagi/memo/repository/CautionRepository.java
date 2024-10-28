package com.fa.sonagi.memo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fa.sonagi.memo.entity.Caution;

public interface CautionRepository extends JpaRepository<Caution, Long> {
	List<Caution> findByBabyId(Long babyId);
}
