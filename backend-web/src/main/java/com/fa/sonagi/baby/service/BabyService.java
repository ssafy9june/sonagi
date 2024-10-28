package com.fa.sonagi.baby.service;

import java.util.List;

import com.fa.sonagi.baby.dto.BabyCodePosDto;
import com.fa.sonagi.baby.dto.BabyCodeResDto;
import com.fa.sonagi.baby.dto.BabyDetailPutDto;
import com.fa.sonagi.baby.dto.BabyDetailResDto;
import com.fa.sonagi.baby.dto.BabyInfoPostDto;
import com.fa.sonagi.baby.dto.BabyInfoResDto;
import com.fa.sonagi.baby.dto.CoparentResDto;

public interface BabyService {
	void registBabyInfo(BabyInfoPostDto babyInfoPostDto);

	BabyCodeResDto getBabyCode(Long babyId);

	void registUserBabyByCode(BabyCodePosDto babyCodePosDto);

	List<BabyInfoResDto> findBabyListByUserId(Long userId);

	void updateBabyDetail(BabyDetailPutDto babyDetailPutDto);

	BabyDetailResDto findBabyDetail(Long babyId, Long userId);

	List<CoparentResDto> findCoparentListByBabyId(Long babyId, Long userId);

	void deleteCoparent(Long babyId, Long coparentId);

	void deleteBabyInfo(Long babyId);

	List<BabyDetailResDto> findDeletedBabyInfoList();

	void restoreBabyInfo(Long babyId);
}
