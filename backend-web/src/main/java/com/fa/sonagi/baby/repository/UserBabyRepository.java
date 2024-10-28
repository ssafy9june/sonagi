package com.fa.sonagi.baby.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fa.sonagi.baby.entity.Baby;
import com.fa.sonagi.baby.entity.UserBaby;
import com.fa.sonagi.user.entity.Users;

public interface UserBabyRepository extends JpaRepository<UserBaby, Long> {
	List<UserBaby> findByUser(Optional<Users> user);

	List<UserBaby> findByBabyId(Long babyId);

	UserBaby findByBabyAndUser(Baby baby, Users user);
}
