package com.fa.sonagi.user.repository;

import static com.fa.sonagi.user.entity.QUsers.*;

import com.fa.sonagi.user.dto.NameDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryCustom {
	private final JPAQueryFactory queryFactory;

	@Override
	public NameDto findName(Long userId) {
		NameDto name = queryFactory
			.select(Projections.bean(NameDto.class,
				users.userId.as("userId"),
				users.name))
			.from(users)
			.where(users.userId.eq(userId))
			.fetchOne();

		return name;
	}
}
