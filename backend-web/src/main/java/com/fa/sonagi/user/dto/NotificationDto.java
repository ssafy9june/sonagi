package com.fa.sonagi.user.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationDto {
	private Long userId;
	private String vAlarm;
	private String cAlarm;
	private String dAlarm;
	private String mAlarm;
}
