package com.fa.sonagi.record.activity.dto;

import java.time.LocalTime;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ActivityPutDto {
	@NotNull
	private Long activityId;

	@NotNull
	private LocalTime createdTime;

	@NotNull
	private LocalTime endTime;

	private String memo;
}
