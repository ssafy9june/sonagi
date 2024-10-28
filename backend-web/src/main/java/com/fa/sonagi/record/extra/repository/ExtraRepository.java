package com.fa.sonagi.record.extra.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fa.sonagi.record.extra.entity.Extra;

public interface ExtraRepository extends JpaRepository<Extra, Long>, ExtraRepositoryCustom {
	List<Extra> findByBabyIdAndCreatedDateOrderByCreatedTimeAsc(Long babyId, LocalDate createdDate);
}
