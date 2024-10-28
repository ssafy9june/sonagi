package com.fa.sonagi.record.sleep.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fa.sonagi.record.sleep.entity.Sleep;

public interface SleepRepository extends JpaRepository<Sleep, Long>, SleepRepositoryCustom {
	List<Sleep> findByBabyIdAndCreatedDateOrderByCreatedTimeAsc(Long babyId, LocalDate createdDate);
}
