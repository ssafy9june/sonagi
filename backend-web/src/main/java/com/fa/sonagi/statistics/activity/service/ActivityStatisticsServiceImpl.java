package com.fa.sonagi.statistics.activity.service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fa.sonagi.record.activity.repository.PlayRepository;
import com.fa.sonagi.record.activity.repository.TummytimeRepository;
import com.fa.sonagi.record.allCategory.dto.StatisticsTime;
import com.fa.sonagi.record.allCategory.dto.StatisticsTimeLong;
import com.fa.sonagi.statistics.activity.dto.ActivityStatisticsDayForWeekDto;
import com.fa.sonagi.statistics.activity.dto.ActivityStatisticsResDto;
import com.fa.sonagi.statistics.activity.dto.ActivityStatisticsWeekResDto;
import com.fa.sonagi.statistics.common.dto.EndTimes;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ActivityStatisticsServiceImpl implements ActivityStatisticsService {

	private final PlayRepository playRepository;
	private final TummytimeRepository tummytimeRepository;
	private final int WEEK = 7;
	private final LocalTime FIRST_TIME = LocalTime.of(0, 0);
	private final LocalTime LAST_TIME = LocalTime.of(23, 59, 59);

	/**
	 * 놀이 통계 계산
	 */
	@Override
	public ActivityStatisticsResDto getActivityStatisticsDay(Long babyId, LocalDate createdDate) {
		ActivityStatisticsResDto activityStatisticsResDto = new ActivityStatisticsResDto();

		List<EndTimes> activityDay = new ArrayList<>();
		// 데이터 조회
		List<EndTimes> plays = playRepository.findPlayByDay(babyId, createdDate);
		int checkIdx = checkTimeDay(plays);
		if (checkIdx != -1)
			plays.get(checkIdx).setEndTime(LAST_TIME);

		List<EndTimes> tummytimes = tummytimeRepository.findTummytimeByDay(babyId, createdDate);
		checkIdx = checkTimeDay(tummytimes);
		if (checkIdx != -1)
			tummytimes.get(checkIdx).setCreatedTime(LAST_TIME);

		createdDate = createdDate.minus(1, ChronoUnit.DAYS);

		// 전날 데이터 조회
		List<EndTimes> yesterdayPlays = playRepository.findPlayByDay(babyId, createdDate);
		checkIdx = checkTimeDay(yesterdayPlays);
		if (checkIdx != -1) {
			plays.add(createActivityStatisticsQueryDto(yesterdayPlays.get(checkIdx).getEndTime()));
			yesterdayPlays.get(checkIdx).setEndTime(LAST_TIME);
		}

		List<EndTimes> yesterdayTummytimes = tummytimeRepository.findTummytimeByDay(babyId, createdDate);
		checkIdx = checkTimeDay(yesterdayTummytimes);
		if (checkIdx != -1) {
			tummytimes.add(createActivityStatisticsQueryDto(yesterdayTummytimes.get(checkIdx).getEndTime()));
			yesterdayTummytimes.get(checkIdx).setCreatedTime(LAST_TIME);
		}

		createdDate = createdDate.minus(1, ChronoUnit.DAYS);

		List<EndTimes> pastPlays = playRepository.findPlayByDay(babyId, createdDate);
		checkIdx = checkTimeDay(pastPlays);
		if (checkIdx != -1)
			yesterdayPlays.add(createActivityStatisticsQueryDto(pastPlays.get(checkIdx).getEndTime()));

		List<EndTimes> pastTummytimes = tummytimeRepository.findTummytimeByDay(babyId, createdDate);
		checkIdx = checkTimeDay(pastTummytimes);
		if (checkIdx != -1) {
			pastTummytimes.add(createActivityStatisticsQueryDto(pastTummytimes.get(checkIdx).getEndTime()));
		}

		for (EndTimes et : plays)
			activityDay.add(et);
		for (EndTimes et : tummytimes)
			activityDay.add(et);
		Collections.sort(activityDay, Comparator.comparing(EndTimes::getCreatedTime));
		activityStatisticsResDto.setTimes(activityDay);

		// 카드 통계
		Long activityTime = sumActivityTimeDay(plays) + sumActivityTimeDay(tummytimes);
		long cnt = plays.size() + tummytimes.size();
		activityStatisticsResDto.setCnt(cnt);
		activityStatisticsResDto.setActivityHour(activityTime / 60);
		activityStatisticsResDto.setActivityMinute(activityTime % 60);

		// 놀이 횟수 통계 계산
		long yesterdayCnt = yesterdayPlays.size() + yesterdayTummytimes.size();
		Long cntPercent = getPercent(cnt, yesterdayCnt);
		Long yesterdayCntPercent = getPercent(yesterdayCnt, cnt);
		activityStatisticsResDto.setCntPercent(cntPercent);
		activityStatisticsResDto.setYesterdayCntPercent(yesterdayCntPercent);

		// 놀이 시간 통계 계산
		Long yesterdayActivityTime = sumActivityTimeDay(yesterdayPlays) + sumActivityTimeDay(yesterdayTummytimes);
		Long timePercent = getPercent(activityTime, yesterdayActivityTime);
		Long yesterdayTimePercent = getPercent(yesterdayActivityTime, activityTime);
		activityStatisticsResDto.setActivityPercent(timePercent);
		activityStatisticsResDto.setYesterdayActivityPercent(yesterdayTimePercent);

		return activityStatisticsResDto;
	}

	/**
	 * 주별 통계 계산
	 */
	@Override
	public ActivityStatisticsWeekResDto getActivityStatisticsWeek(Long babyId, LocalDate createdDate) {
		LocalDate startDay = createdDate.minusWeeks(1).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
		startDay = startDay.minusDays(1);
		LocalDate lastDay = createdDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
		ActivityStatisticsWeekResDto activityWeek = new ActivityStatisticsWeekResDto();

		// 일주일 데이터 조회
		List<StatisticsTime> plays = playRepository.findPlayForWeek(babyId, startDay, lastDay);
		List<StatisticsTime> tummytimes = tummytimeRepository.findTummytimeForWeek(babyId, startDay, lastDay);

		// 날짜별 종료시간이 시작시간보다 빠른 경우 데이터 정제
		List<Integer> changeIdxes = checkTimeWeek(plays);
		for (int i = 0; i < changeIdxes.size(); i++) {
			StatisticsTime changeTime = plays.get(changeIdxes.get(i));
			plays.add(createStatisticsTime(changeTime.getEndTime(), changeTime.getCreatedDate().plusDays(1)));
			plays.get(changeIdxes.get(i)).setEndTime(LAST_TIME);
		}

		changeIdxes = checkTimeWeek(tummytimes);
		for (int i = 0; i < changeIdxes.size(); i++) {
			StatisticsTime changeTime = tummytimes.get(changeIdxes.get(i));
			tummytimes.add(createStatisticsTime(changeTime.getEndTime(), changeTime.getCreatedDate().plusDays(1)));
			tummytimes.get(changeIdxes.get(i)).setEndTime(LAST_TIME);
		}

		startDay = startDay.plusDays(1);
		lastDay = lastDay.plusDays(1);

		// 2주에 관한 관한 map의 key 설정
		Map<LocalDate, ActivityStatisticsDayForWeekDto> activityDay = new HashMap<>();
		while (!startDay.isEqual(lastDay)) {
			activityDay.put(startDay, new ActivityStatisticsDayForWeekDto());
			startDay = startDay.plusDays(1);
		}

		// 해당 날짜가 유효한 경우 Time을 Long으로 바꿔서 넣음
		for (int i = 0; i < plays.size(); i++) {
			LocalDate writeDay = plays.get(i).getCreatedDate();
			if (activityDay.containsKey(writeDay)) {
				StatisticsTimeLong writeSleep =
					createStatisticsTimeLong(plays.get(i).getCreatedTime(), plays.get(i).getEndTime());
				activityDay.get(writeDay).getPlays().add(writeSleep);
			}
		}

		for (int i = 0; i < tummytimes.size(); i++) {
			LocalDate writeDay = tummytimes.get(i).getCreatedDate();
			if (activityDay.containsKey(writeDay)) {
				StatisticsTimeLong writeSleep =
					createStatisticsTimeLong(tummytimes.get(i).getCreatedTime(), tummytimes.get(i).getEndTime());
				activityDay.get(writeDay).getTummytimes().add(writeSleep);
			}
		}

		// 날짜별 데이터 세팅
		LocalDate writeDay = createdDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
		for (int i = 0; i < WEEK; i++) {
			activityWeek.getActivityStatistics().put(writeDay.format(DateTimeFormatter.ofPattern("M/dd")), activityDay.get(writeDay));
			writeDay = writeDay.plusDays(1);
		}
		writeDay = writeDay.minusWeeks(1);

		// 카테고리별 일주일 통계 조회
		Long cnt = sumSleepCnt(activityDay, writeDay);
		Long activityTime = sumActivityTimeWeek(activityDay, writeDay);
		activityWeek.setCnt(cnt);
		activityWeek.setActivityHour(activityTime / 60);
		activityWeek.setActivityMinute(activityTime % 60);

		writeDay = writeDay.minusWeeks(1);

		// 놀이 횟수 통계 퍼센트 계산
		Long lastWeekCnt = sumSleepCnt(activityDay, writeDay);
		Long cntPercent = getPercent(cnt, lastWeekCnt);
		Long lastWeekCntPercent = getPercent(lastWeekCnt, cnt);
		activityWeek.setCntPercent(cntPercent);
		activityWeek.setYesterdayCntPercent(lastWeekCntPercent);

		// 놀이 시간 통계 퍼센트 계산
		Long lastWeekActivityTime = sumActivityTimeWeek(activityDay, writeDay);
		Long activityTimePercent = getPercent(activityTime, lastWeekActivityTime);
		Long lastWeekActivityTimePercent = getPercent(lastWeekActivityTime, activityTime);
		activityWeek.setActivityPercent(activityTimePercent);
		activityWeek.setYesterdayActivityPercent(lastWeekActivityTimePercent);

		return activityWeek;
	}

	/**
	 * 퍼센트 계산하기 Long
	 */
	public Long getPercent(Long target, Long opponent) {
		if (target == 0) return 0L;
		else if (target >= opponent) {
			return 100L;
		}
		else {
			return target * 100 / opponent;
		}
	}

	/**
	 * 총 시간 계산 - day
	 */
	public Long sumActivityTimeDay(List<EndTimes> activities) {
		Long activityTime = 0L;

		for (int i = 0; i < activities.size(); i++) {
			long startTime =
				activities.get(i).getCreatedTime().getHour() * 60 + activities.get(i).getCreatedTime().getMinute();
			long endTime = activities.get(i).getEndTime().getHour() * 60 + activities.get(i).getEndTime().getMinute();
			long passTime = endTime - startTime;

			activityTime += passTime;
		}

		return activityTime;
	}

	/**
	 * 총 시간 계산 - week
	 */
	public Long sumActivityTimeWeek(Map<LocalDate, ActivityStatisticsDayForWeekDto> activities, LocalDate writeDay) {
		Long activityTime = 0L;

		for (int i = 0; i < WEEK; i++) {
			List<StatisticsTimeLong> activityDay = activities.get(writeDay).getPlays();
			for (int j = 0; j < activityDay.size(); j++) {
				activityTime += activityDay.get(j).getPassTime();
			}

			activityDay = activities.get(writeDay).getTummytimes();
			for (int j = 0; j < activityDay.size(); j++) {
				activityTime += activityDay.get(j).getPassTime();
			}

			writeDay = writeDay.plusDays(1);
		}

		return activityTime;
	}

	/**
	 * 총 횟수 계산
	 */
	public Long sumSleepCnt(Map<LocalDate, ActivityStatisticsDayForWeekDto> activities, LocalDate writeDay) {
		Long cnt = 0L;

		for (int i = 0; i < WEEK; i++) {
			cnt += activities.get(writeDay).getPlays().size();
			cnt += activities.get(writeDay).getTummytimes().size();

			writeDay = writeDay.plusDays(1);
		}

		return cnt;
	}

	/**
	 * 오전 12시 기준으로 이전에 시작해서 이후에 끝나는 기록이 있는지 확인
	 */
	public int checkTimeDay(List<EndTimes> activities) {
		for (int i = 0; i < activities.size(); i++) {
			if (activities.get(i).getEndTime().isBefore(activities.get(i).getCreatedTime())) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 오전 12시 기준으로 이전에 시작해서 이후에 끝나는 기록이 있는지 확인
	 */
	public List<Integer> checkTimeWeek(List<StatisticsTime> activities) {
		List<Integer> idxList = new ArrayList<>();
		for (int i = 0; i < activities.size(); i++) {
			if (activities.get(i).getEndTime().isBefore(activities.get(i).getCreatedTime())) {
				idxList.add(i);
			}
		}
		return idxList;
	}

	/**
	 * 12시 기준으로 전날에 기록이 존재하면 12시 이후로 추가하기 - Day
	 */
	public EndTimes createActivityStatisticsQueryDto(LocalTime endTime) {
		EndTimes activityStatisticsQueryDto = new EndTimes();
		activityStatisticsQueryDto.setCreatedTime(FIRST_TIME);
		activityStatisticsQueryDto.setEndTime(endTime);

		return activityStatisticsQueryDto;
	}

	/**
	 * 12시 기준으로 전날에 기록이 존재하면 12시 이후로 추가하기 - Week
	 */
	public StatisticsTime createStatisticsTime(LocalTime endTime, LocalDate writeDay) {
		StatisticsTime statisticsTime = new StatisticsTime();
		statisticsTime.setCreatedDate(writeDay);
		statisticsTime.setCreatedTime(FIRST_TIME);
		statisticsTime.setEndTime(endTime);

		return statisticsTime;
	}

	/**
	 * 시작 시간과 흘러간 시간을 Long 값으로 바꿔줌
	 */
	public StatisticsTimeLong createStatisticsTimeLong(LocalTime createTime, LocalTime endTime) {
		StatisticsTimeLong statisticsTimeLong = new StatisticsTimeLong();
		Long startTime = (long)(createTime.getHour() * 60 + createTime.getMinute());
		statisticsTimeLong.setCreatedTime(startTime);
		Long passTime = endTime.getHour() * 60 + endTime.getMinute() - startTime;
		statisticsTimeLong.setPassTime(passTime);

		return statisticsTimeLong;
	}
}
