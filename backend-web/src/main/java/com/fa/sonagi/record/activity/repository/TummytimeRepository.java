package com.fa.sonagi.record.activity.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fa.sonagi.record.activity.entity.Tummytime;

public interface TummytimeRepository extends JpaRepository<Tummytime, Long>, TummytimeRepositoryCustom {
	List<Tummytime> findByBabyIdAndCreatedDateOrderByCreatedTimeAsc(Long babyId, LocalDate createdDate);
}
