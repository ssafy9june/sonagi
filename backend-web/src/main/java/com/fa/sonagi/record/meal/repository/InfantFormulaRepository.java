package com.fa.sonagi.record.meal.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fa.sonagi.record.meal.entity.InfantFormula;

public interface InfantFormulaRepository extends JpaRepository<InfantFormula, Long>, InfantFormulaRepositoryCustom {

	List<InfantFormula> findByBabyIdAndCreatedDateOrderByCreatedTimeAsc(Long babyId, LocalDate createdDate);
}
