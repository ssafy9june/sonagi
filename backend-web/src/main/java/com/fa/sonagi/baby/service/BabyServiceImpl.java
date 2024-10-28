package com.fa.sonagi.baby.service;

import java.time.LocalDate;
import java.util.Base64;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fa.sonagi.baby.dto.BabyCodePosDto;
import com.fa.sonagi.baby.dto.BabyCodeResDto;
import com.fa.sonagi.baby.dto.BabyDetailPutDto;
import com.fa.sonagi.baby.dto.BabyDetailResDto;
import com.fa.sonagi.baby.dto.BabyInfoPostDto;
import com.fa.sonagi.baby.dto.BabyInfoResDto;
import com.fa.sonagi.baby.dto.CoparentResDto;
import com.fa.sonagi.baby.entity.Baby;
import com.fa.sonagi.baby.entity.UserBaby;
import com.fa.sonagi.baby.repository.BabyRepository;
import com.fa.sonagi.baby.repository.UserBabyRepository;
import com.fa.sonagi.immunization.entity.Checkup;
import com.fa.sonagi.immunization.entity.CheckupStatus;
import com.fa.sonagi.immunization.entity.Vaccination;
import com.fa.sonagi.immunization.entity.VaccinationStatus;
import com.fa.sonagi.immunization.repository.CheckupRepository;
import com.fa.sonagi.immunization.repository.CheckupStatusRepository;
import com.fa.sonagi.immunization.repository.VaccinationRepository;
import com.fa.sonagi.immunization.repository.VaccinationStatusRepository;
import com.fa.sonagi.user.entity.Users;
import com.fa.sonagi.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BabyServiceImpl implements BabyService {

	private final BabyRepository babyRepository;
	private final UserRepository userRepository;
	private final UserBabyRepository userBabyRepository;
	private final CheckupStatusRepository checkupStatusRepository;
	private final CheckupRepository checkupRepository;
	private final VaccinationStatusRepository vaccinationStatusRepository;
	private final VaccinationRepository vaccinationRepository;

	/**
	 * 아기 정보 등록
	 */
	@Override
	@Transactional
	public void registBabyInfo(BabyInfoPostDto babyInfoPostDto) {
		Baby baby = Baby.builder()
			.birthDate(babyInfoPostDto.getBirthDate())
			.gender(babyInfoPostDto.getGender())
			.name(babyInfoPostDto.getName())
			.code(null)
			.lastMealTime(null)
			.lastDiaryTime(null)
			.deletedAt(null)
			.isDeleted("N")
			.build();

		babyRepository.save(baby);

		baby.updateCode(createBabyCode(babyInfoPostDto.getUserId(), baby.getId()));

		Users user = userRepository.findById(babyInfoPostDto.getUserId()).orElseThrow();
		registUserBaby(user, baby, "Y");
		registCheckup(baby);
		registVaccination(baby);
	}

	/**
	 * 아기 정보 코드 생성
	 */
	public String createBabyCode(Long userId, Long babyId) {
		String specialCode = userId + "angel" + babyId ;

		byte[] encodedBytes = Base64.getEncoder().encode(specialCode.getBytes());

		return new String(encodedBytes);
	}

	/**
	 * 아기 코드 조회
	 */
	@Override
	public BabyCodeResDto getBabyCode(Long babyId) {
		BabyCodeResDto babyCodeResDto = babyRepository.findBabyCodeByBabyId(babyId);
		return babyCodeResDto;
	}

	/**
	 * 아기의 아이디랑 유저 아이디 매칭
	 */
	public void registUserBaby(Users user, Baby baby, String authority) {

		UserBaby userBaby = UserBaby.builder()
			.user(user)
			.baby(baby)
			.authority(authority)
			.build();

		userBabyRepository.save(userBaby);
	}

	/**
	 * 아기 코드로 아기를 찾아 아기의 아이디랑 유저 아이디 매칭
	 */
	@Override
	@Transactional
	public void registUserBabyByCode(BabyCodePosDto babyCodePosDto) {
		Baby baby = babyRepository.findByCode(babyCodePosDto.getCode());
		Users user = userRepository.findById(babyCodePosDto.getUserId()).orElseThrow();

		registUserBaby(user, baby, "N");
	}

	/**
	 * CheckupStatus 생성
	 */
	@Transactional
	public void registCheckup(Baby baby) {
		List<Checkup> checkups = checkupRepository.findAll();

		for (Checkup checkup : checkups) {
			CheckupStatus checkupStatus = CheckupStatus.builder()
				.baby(baby)
				.checkup(checkup)
				.build();
			checkupStatusRepository.save(checkupStatus);
		}

	}

	/**
	 * VaccinationStatus 생성
	 */
	@Transactional
	public void registVaccination(Baby baby) {
		List<Vaccination> vaccinations = vaccinationRepository.findAll();

		for (Vaccination vaccination : vaccinations) {
			VaccinationStatus vaccinationStatus = VaccinationStatus.builder()
				.baby(baby)
				.vaccination(vaccination)
				.build();
			vaccinationStatusRepository.save(vaccinationStatus);
		}

	}

	/**
	 * 아기 정보 조회(상단바)
	 */
	@Override
	public List<BabyInfoResDto> findBabyListByUserId(Long userId) {
		Optional<Users> byId = userRepository.findById(userId);
		List<UserBaby> userBabies = userBabyRepository.findByUser(byId);

		return userBabies.stream()
			.filter(u -> "N".equals(u.getBaby().getIsDeleted()))
			.map(u -> BabyInfoResDto.builder()
				.babyId(u.getBaby().getId())
				.name(u.getBaby().getName())
				.gender(u.getBaby().getGender())
				.authority(u.getAuthority())
				.build())
			.collect(Collectors.toList());
	}

	/**
	 * 아기 상세 정보 수정
	 */
	@Override
	@Transactional
	public void updateBabyDetail(BabyDetailPutDto babyDetailPutDto) {
		Baby baby = babyRepository.findById(babyDetailPutDto.getBabyId()).orElseThrow();
		baby.updateBaby(babyDetailPutDto.getName(), babyDetailPutDto.getGender(), babyDetailPutDto.getBirthDate());

	}

	/**
	 * 아기 상세 정보 조회
	 */
	@Override
	public BabyDetailResDto findBabyDetail(Long babyId, Long userId) {
		Baby baby = babyRepository.findById(babyId).orElseThrow();
		Users user = userRepository.findById(userId).orElseThrow();
		UserBaby userBaby = userBabyRepository.findByBabyAndUser(baby, user);

		if ("N".equals(baby.getIsDeleted())) {
			return BabyDetailResDto.builder()
				.babyId(baby.getId())
				.name(baby.getName())
				.gender(baby.getGender())
				.birthDate(baby.getBirthDate())
				.authority(userBaby.getAuthority())
				.isDeleted(baby.getIsDeleted())
				.build();
		} else {
			// 'isDeleted'가 'N'이 아닌 경우에는 null 또는 에러 처리 등을 수행할 수 있습니다.
			return null;
		}
	}

	/**
	 * 공동양육자 리스트 조회
	 */
	@Override
	public List<CoparentResDto> findCoparentListByBabyId(Long babyId, Long userId) {
		BabyDetailResDto babyDetail = findBabyDetail(babyId, userId);
		// 사용자가 주양육자인 경우에만 조회
		if (babyDetail != null) {
			List<UserBaby> users = userBabyRepository.findByBabyId(babyId);

			return users.stream()
				.filter(u -> !u.getUser().getUserId().equals(userId)) // userId가 다른 경우만 필터링
				.map(u -> CoparentResDto.builder()
					.userId(u.getUser().getUserId())
					.name(u.getUser().getName())
					.build())
				.collect(Collectors.toList());
		} else {
			return null;
		}

	}

	/**
	 * 주양육자가 공동양육자 삭제 & 공동양육자가 아이 정보 제거
	 */
	@Override
	@Transactional
	public void deleteCoparent(Long babyId, Long coparentId) {
		Baby baby = babyRepository.findById(babyId).orElseThrow();
		Users user = userRepository.findById(coparentId).orElseThrow();
		UserBaby userBaby = userBabyRepository.findByBabyAndUser(baby, user);
		userBabyRepository.delete(userBaby);
	}

	/**
	 * 주양육자가 아이 정보 삭제
	 */
	@Override
	@Transactional
	public void deleteBabyInfo(Long babyId) {
		Baby babyInfo = babyRepository.findById(babyId).orElseThrow();
		babyInfo.deleteBabyInfo(LocalDate.now(), "Y");
	}

	/**
	 * 삭제된 아이 데이터 리스트 조회
	 */
	@Override
	public List<BabyDetailResDto> findDeletedBabyInfoList() {
		List<Baby> babies = babyRepository.findAll();
		return babies.stream()
			.filter(b -> b.getIsDeleted().equals("Y"))
			.sorted(Comparator.comparing(Baby::getDeletedAt)) // 최근 날짜 순으로 정렬
			.map(b -> BabyDetailResDto.builder()
				.babyId(b.getId())
				.name(b.getName())
				.birthDate(b.getBirthDate())
				.gender(b.getGender())
				.isDeleted(b.getIsDeleted())
				.deletedAt(b.getDeletedAt())
				.build())
			.collect(Collectors.toList());
	}

	/**
	 * 아이 정보 복원
	 */
	@Override
	@Transactional
	public void restoreBabyInfo(Long babyId) {
		Baby babyInfo = babyRepository.findById(babyId).orElseThrow();
		babyInfo.deleteBabyInfo(null, "N");
	}
}
