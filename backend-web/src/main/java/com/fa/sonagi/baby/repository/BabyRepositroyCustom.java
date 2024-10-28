package com.fa.sonagi.baby.repository;

import com.fa.sonagi.baby.dto.BabyCodeResDto;

public interface BabyRepositroyCustom {
	BabyCodeResDto findBabyCodeByBabyId(Long babyId);
}
