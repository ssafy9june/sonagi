package com.fa.sonagi.record.activity.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fa.sonagi.record.activity.entity.Play;

public interface PlayRepository extends JpaRepository<Play, Long>, PlayRepositoryCustom {
	List<Play> findByBabyIdAndCreatedDateOrderByCreatedTimeAsc(Long babyId, LocalDate createdDate);
}
