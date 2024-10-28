package com.fa.sonagi.memo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fa.sonagi.memo.entity.Illness;

public interface IllnessRepository extends JpaRepository<Illness, Long> {
	List<Illness> findByBabyId(Long babyId);
}
