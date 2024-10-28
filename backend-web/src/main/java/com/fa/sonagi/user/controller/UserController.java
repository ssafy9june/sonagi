package com.fa.sonagi.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fa.sonagi.user.dto.FCMTokenDto;
import com.fa.sonagi.user.dto.NameDto;
import com.fa.sonagi.user.dto.NotificationDto;
import com.fa.sonagi.user.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "User", description = "사용자 API")
@Slf4j
@RequestMapping("/api")
@RestController
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;

	/**
	 * 사용자 이름 조회
	 */
	@GetMapping("/name/{userId}")
	@Operation(summary = "유저 아이디로 해당 유저의 이름을 조회함")
	public ResponseEntity<?> getName(@PathVariable Long userId) {
		NameDto nameDto = userService.findName(userId);
		return ResponseEntity.ok().body(nameDto);
	}

	/**
	 * 사용자 이름 등록, 수정
	 */
	@PutMapping("/name")
	@Operation(summary = "유저의 이름을 수정함")
	public ResponseEntity<?> updateName(@RequestBody NameDto nameDto) {
		userService.updateName(nameDto);
		return ResponseEntity.ok().build();
	}

	/**
	 * FCM 토큰 업데이트
	 */
	@PutMapping("/fcm")
	public ResponseEntity<?> updateFCMToken(@RequestBody FCMTokenDto fcmTokenDto) {
		log.info("test : {} ", fcmTokenDto.getFirebaseToken());
		userService.updateFCMToken(fcmTokenDto);
		return ResponseEntity.ok().build();
	}

	/**
	 * 알림 조회
	 */
	@GetMapping("/notification/{userId}")
	@Operation(summary = "유저 아이디로 해당 유저의 알림을 조회함")
	public ResponseEntity<?> getNotification(@PathVariable Long userId) {
		NotificationDto notificationDto = userService.findNotification(userId);
		return ResponseEntity.ok().body(notificationDto);
	}

	/**
	 * 접종 알림 수정
	 */
	@PutMapping("/valarm/{userId}/{alarm}")
	@Operation(summary = "유저 아이디와 alarm으로 해당 유저의 접종 알림 여부를 수정함")
	public ResponseEntity<?> updateVAlarm(@PathVariable Long userId, @PathVariable Long alarm) {
		String check = (alarm == 0) ? "N" : "Y";
		userService.updateVAlarm(userId, check);
		return ResponseEntity.ok().build();
	}

	/**
	 * 식사 알림 수정
	 */
	@PutMapping("/malarm/{userId}/{alarm}")
	@Operation(summary = "유저 아이디와 alarm으로 해당 유저의 식사 알림 여부를 수정함")
	public ResponseEntity<?> updateMAlarm(@PathVariable Long userId, @PathVariable Long alarm) {
		String check = (alarm == 0) ? "N" : "Y";
		userService.updateMAlarm(userId, check);
		return ResponseEntity.ok().build();
	}

	/**
	 * 일기 알림 수정
	 */
	@PutMapping("/dalarm/{userId}/{alarm}")
	@Operation(summary = "유저 아이디와 alarm으로 해당 유저의 일기 알림 여부를 수정함")
	public ResponseEntity<?> updateDAlarm(@PathVariable Long userId, @PathVariable Long alarm) {
		String check = (alarm == 0) ? "N" : "Y";
		userService.updateDAlarm(userId, check);
		return ResponseEntity.ok().build();
	}

	/**
	 * 검진 알림 수정
	 */
	@PutMapping("/calarm/{userId}/{alarm}")
	@Operation(summary = "유저 아이디와 alarm으로 해당 유저의 검진 알림 여부를 수정함")
	public ResponseEntity<?> updateCAlarm(@PathVariable Long userId, @PathVariable Long alarm) {
		String check = (alarm == 0) ? "N" : "Y";
		userService.updateCAlarm(userId, check);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/logout")
	@Operation(summary = "유저가 요청을 보내면 쿠키의 RefreshToken과 Redis의 저장된 토큰을 지운다.")
	public ResponseEntity<?> logout(HttpServletRequest request, HttpServletResponse response) {
		userService.logout(request, response);
		return ResponseEntity.ok().build();
	}
}
