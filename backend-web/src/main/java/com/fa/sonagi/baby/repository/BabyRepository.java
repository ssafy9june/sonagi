package com.fa.sonagi.baby.repository;

import com.fa.sonagi.baby.entity.Baby;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BabyRepository extends JpaRepository<Baby, Long>, BabyRepositroyCustom {
	Baby findByCode(String babyCode);
}
