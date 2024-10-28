package com.fa.sonagi.FCM.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.fa.sonagi.baby.dto.CoparentResDto;
import com.fa.sonagi.baby.entity.Baby;
import com.fa.sonagi.baby.entity.UserBaby;
import com.fa.sonagi.baby.repository.BabyRepository;
import com.fa.sonagi.baby.repository.UserBabyRepository;
import com.fa.sonagi.immunization.entity.CheckupStatus;
import com.fa.sonagi.immunization.entity.VaccinationStatus;
import com.fa.sonagi.immunization.repository.CheckupStatusRepository;
import com.fa.sonagi.immunization.repository.VaccinationStatusRepository;
import com.fa.sonagi.user.entity.Users;
import com.fa.sonagi.user.repository.UserRepository;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class FCMNotificationService {
	private final FirebaseMessaging firebaseMessaging;
	private final UserRepository userRepository;
	private final BabyRepository babyRepository;
	private final UserBabyRepository userBabyRepository;
	private final VaccinationStatusRepository vaccinationStatusRepository;
	private final CheckupStatusRepository checkupStatusRepository;

	/**
	 * 식사 알림
	 * 실행 주기 : 30분
	 * 알림 보내는 주기 : 마지막 기록 후 2시간 30분 이후
	 */
	// @Scheduled(fixedRate = 1800000) // 30분(1800초)마다 실행
	public void sendMealNotificatioin() {
		List<Baby> babies = babyRepository.findAll();

		// last_diary_time 또는 last_meal_time 중 두시간 반 이상 지난 경우만 필터링
		// 마지막 식사 기록 시간 조회
		List<Baby> mealFilter = babies.stream()
			.filter(
				b -> b.getLastMealTime() != null && b.getMealNotification().equals("N") && b.getLastMealTime()
					.isBefore(b.getLastMealTime().plusMinutes(150)))
			.collect(Collectors.toList());
		mealNotification(mealFilter);
	}

	/**
	 * 검진, 접종 알림
	 * 실행 주기 : 매일 아침 9시
	 * 접종하지 않았을 경우 아기 별 접종, 검진 예정일 별 알림 전송
	 */
	// @Scheduled(cron = "0 0 9 * * *") // 매일 아침 9시에 실행
	public void sendImmunization() {
		// 접종 시작 일 조회
		List<VaccinationStatus> vaccinationStatuses = vaccinationStatusRepository.findAll();
		// 접종 시작 일이 오늘인 아기
		List<VaccinationStatus> vaccination = vaccinationStatuses.stream()
			// 접종하지 않고 접종 시작 예정일이 오늘인 경우
			.filter(v -> v.getVaccinationDate() == null && v.getBaby()
				.getBirthDate()
				.plusDays(v.getVaccination().getStartDate())
				.isEqual(LocalDate.now()))
			.collect(Collectors.toList());

		vaccinationNotification(vaccination);

		// 검진 시작 일 조회
		List<CheckupStatus> checkupStatuses = checkupStatusRepository.findAll();

		// 검진하지 않고 검진 시작 예정일이 오늘인 경우
		List<CheckupStatus> checkup = checkupStatuses.stream()
			.filter(v -> v.getCheckupDate() == null && v.getBaby()
				.getBirthDate()
				.plusDays(v.getCheckup().getStartDate())
				.isEqual(LocalDate.now()))
			.collect(Collectors.toList());
		checkupNotification(checkup);
	}

	/**
	 * 일기 알림
	 * 실행 주기 : 매일 저녁 9시
	 * 한 번도 일기를 작성하지 않았거나 오늘 일기를 작성하지 않은 경우 알림 전송
	 */
	// @Scheduled(cron = "0 0 21 * * *") // 매일 저녁 9시에 실행
	public void sendDiaryNotification() {
		List<Baby> babies = babyRepository.findAll();
		// 마지막 일기 기록 시간 조회
		List<Baby> diaryFilter = babies.stream()
			.filter(
				// 일기를 작성한 적 없거나, 마지막 작성일이 오늘이 아닌 경우
				b -> b.getLastDiaryTime() == null || !(b.getLastDiaryTime().toLocalDate().isEqual(LocalDate.now())))
			.collect(Collectors.toList());
		diaryNotification(diaryFilter);
	}

	public void sendWritedDiaryNotification(List<CoparentResDto> coparentResDtos) {
		for (CoparentResDto coparents : coparentResDtos) {
			Long userId = coparents.getUserId();
			Users user = userRepository.findById(userId).orElseThrow();

			if (user.getDAlarm().equals("Y")) {
				System.out.println("사용자" + user.getUserId());
				sendNotificationByToken(userId, "작성된 일기 알림", "우리 아이의 일기가 작성되었어요!");
			}
		}
	}

	public void mealNotification(List<Baby> mealFilter) {
		System.out.println("dfjskfjaldjfaslfh");
		for (Baby baby : mealFilter) {
			List<UserBaby> userBabies = userBabyRepository.findByBabyId(baby.getId());

			for (UserBaby userBaby : userBabies) {
				Long userId = userBaby.getUser().getUserId();
				Users user = userRepository.findById(userId).orElseThrow();
				// 식사 알람
				// 사용자가 알림을 켜놓은 경우
				if (user.getMAlarm().equals("Y")) {
					System.out.println("사용자" + user.getUserId());
					sendNotificationByToken(userId, "식사 알림", "혹시 식사 기록을 잊으셨나요?");
				}
			}
			baby.updateMealOn("Y"); // 알림 상태 Y로 변경
			babyRepository.save(baby);
		}
	}

	public void diaryNotification(List<Baby> diaryFilter) {
		for (Baby baby : diaryFilter) {
			List<UserBaby> userBabies = userBabyRepository.findByBabyId(baby.getId());

			for (UserBaby userBaby : userBabies) {
				Long userId = userBaby.getUser().getUserId();
				Users user = userRepository.findById(userId).orElseThrow();

				// 일기 알람
				if (user.getDAlarm() == "Y") {
					sendNotificationByToken(userId, "일기 알림", "혹시 일기 작성을 잊으셨나요?");
				}
			}
		}
	}

	public void vaccinationNotification(List<VaccinationStatus> vaccination) {
		for (VaccinationStatus status : vaccination) {
			Baby baby = status.getBaby();
			List<UserBaby> userBabies = userBabyRepository.findByBabyId(baby.getId());
			for (UserBaby userBaby : userBabies) {
				Long userId = userBaby.getUser().getUserId();
				Users user = userRepository.findById(userId).orElseThrow();

				// 접종 알람
				if (user.getVAlarm().equals("Y")) {
					String result = sendNotificationByToken(userId, "접종 알림", "접종 정보를 확인하세요!");

					System.out.println("접종" + result);
				}
			}
		}
	}

	public void checkupNotification(List<CheckupStatus> checkup) {
		for (CheckupStatus status : checkup) {
			Baby baby = status.getBaby();
			List<UserBaby> userBabies = userBabyRepository.findByBabyId(baby.getId());

			for (UserBaby userBaby : userBabies) {
				Long userId = userBaby.getUser().getUserId();
				Users user = userRepository.findById(userId).orElseThrow();

				// 접종 알람
				if (user.getCAlarm().equals("Y")) {
					String result = sendNotificationByToken(userId, "검진 알림", "검진 정보를 확인하세요!");

					System.out.println("검진" + result);
				}
			}
		}
	}

	public String sendNotificationByToken(Long userId, String title, String content) {
		Optional<Users> user = userRepository.findById(userId);
		if (user.isEmpty())
			return "user is not exist";

		if (user
			.get()
			.getFirebaseToken() != null) {
			Notification notification = Notification
				.builder()
				.setTitle(title)
				.setBody(content)
				// .setImage()
				.build();
			Message message = Message
				.builder()
				.putData("title", title)
				.putData("content", content)
				.setToken(user
					.get()
					.getFirebaseToken())
				.setNotification(notification)
				.build();
			try {
				firebaseMessaging.send(message);
				return "알림을 성공적으로 전송했습니다. targetUserId = " + userId;
			} catch (FirebaseMessagingException e) {
				e.printStackTrace();
				return "알림 보내기를 실패하였습니다. targetUserId = " + userId;
			}
		} else {
			return "서버에 저장된 유저의 Firebase Token이 존재하지 않습니다. targertUserId = " + userId;
		}

	}
}