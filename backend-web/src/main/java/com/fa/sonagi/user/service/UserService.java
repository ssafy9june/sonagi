package com.fa.sonagi.user.service;

import com.fa.sonagi.user.dto.FCMTokenDto;
import com.fa.sonagi.user.dto.NameDto;
import com.fa.sonagi.user.dto.NotificationDto;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {

	void updateName(NameDto nameDto);

	NameDto findName(Long userId);

	void updateFCMToken(FCMTokenDto fcmTokenDto);

	NotificationDto findNotification(Long userId);

	void updateVAlarm(Long userId, String vAlarm);

	void updateMAlarm(Long userId, String mAlarm);

	void updateDAlarm(Long userId, String dAlarm);

	void updateCAlarm(Long userId, String cAlarm);

	void logout( HttpServletRequest request,HttpServletResponse response);
}
