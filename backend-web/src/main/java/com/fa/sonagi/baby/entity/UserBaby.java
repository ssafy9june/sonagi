package com.fa.sonagi.baby.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import com.fa.sonagi.user.entity.Users;
import com.fa.sonagi.user.utils.BaseTimeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@IdClass(UserBabyId.class)
@Table(name = "user_baby")
public class UserBaby extends BaseTimeEntity {

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", columnDefinition = "BIGINT UNSIGNED")
	private Users user;

	@Id
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "baby_id")
	private Baby baby;

	@Column(name = "authority", length = 2, nullable = false, updatable = false)
	String authority;
}