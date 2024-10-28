package com.fa.sonagi.immunization.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fa.sonagi.immunization.entity.CheckupStatus;

public interface CheckupStatusRepository extends JpaRepository<CheckupStatus, Long> {
	List<CheckupStatus> findByBabyId(Long babyId);

	CheckupStatus findByBabyIdAndCheckupId(Long babyId, Long checkupId);
}
