package com.fa.sonagi.memo.entity;

import com.fa.sonagi.user.entity.Users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "illness")
public class Illness {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "illness_id", nullable = false)
	private Long id;

	@Column(name = "baby_id")
	private Long babyId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private Users user;

	@Column(name = "memo", length = 300)
	private String memo;

	public void updateIllness(String memo) {
		this.memo = memo;
	}
}
