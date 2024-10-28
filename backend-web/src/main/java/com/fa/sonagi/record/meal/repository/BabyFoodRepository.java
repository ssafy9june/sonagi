package com.fa.sonagi.record.meal.repository;

import java.time.LocalDate;
import java.util.List;

import com.fa.sonagi.record.meal.entity.BabyFood;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BabyFoodRepository extends JpaRepository<BabyFood, Long>, BabyFoodRepositoryCustom{
	List<BabyFood> findByBabyIdAndCreatedDateOrderByCreatedTimeAsc(Long babyId, LocalDate createdDate);
}
