package com.fa.sonagi.user.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FCMTokenDto {
	private Long userId;
	private String firebaseToken;
}
