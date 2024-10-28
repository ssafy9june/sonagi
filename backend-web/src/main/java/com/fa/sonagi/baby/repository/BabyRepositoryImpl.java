package com.fa.sonagi.baby.repository;

import com.fa.sonagi.baby.dto.BabyCodeResDto;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

import static com.fa.sonagi.baby.entity.QBaby.baby;

@RequiredArgsConstructor
public class BabyRepositoryImpl implements BabyRepositroyCustom{

	private final JPAQueryFactory queryFactory;

	@Override
	public BabyCodeResDto findBabyCodeByBabyId(Long babyId) {
		BabyCodeResDto babyCode = queryFactory
			.select(Projections.bean(BabyCodeResDto.class,
				baby.code))
			.from(baby)
			.where(baby.id.eq(babyId))
			.fetchOne();

		return babyCode;
	}
}
