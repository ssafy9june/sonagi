package com.fa.sonagi.statistics.sleep.service;

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

import com.fa.sonagi.record.allCategory.dto.StatisticsTime;
import com.fa.sonagi.record.allCategory.dto.StatisticsTimeLong;
import com.fa.sonagi.record.sleep.repository.SleepRepository;
import com.fa.sonagi.statistics.common.dto.EndTimes;
import com.fa.sonagi.statistics.sleep.dto.SleepStatisticsDayForWeekDto;
import com.fa.sonagi.statistics.sleep.dto.SleepStatisticsResDto;
import com.fa.sonagi.statistics.sleep.dto.SleepStatisticsWeekResDto;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SleepStatisticsServiceImpl implements SleepStatisticsService{

	private final SleepRepository sleepRepository;
	private final int WEEK = 7;
	private final LocalTime FIRST_TIME = LocalTime.of(0, 0);
	private final LocalTime LAST_TIME = LocalTime.of(23, 59, 59);

	/**
	 * 일별 통계 계산
	 */
	@Override
	public SleepStatisticsResDto getSleepStatisticsDay(Long babyId, LocalDate createdDate) {
		SleepStatisticsResDto sleepStatisticsResDto = new SleepStatisticsResDto();

		List<EndTimes> sleepDay = new ArrayList<>();
		// 데이터 조회
		List<EndTimes> sleeps = sleepRepository.findSleepByDay(babyId, createdDate);
		int checkIdx = checkTimeDay(sleeps);
		if (checkIdx != -1)
			sleeps.get(checkIdx).setEndTime(LAST_TIME);

		createdDate = createdDate.minus(1, ChronoUnit.DAYS);

		// 전날 수면 데이터 조회
		List<EndTimes> yesterdaySleeps = sleepRepository.findSleepByDay(babyId, createdDate);
		checkIdx = checkTimeDay(yesterdaySleeps);
		if (checkIdx != -1) {
			sleeps.add(createSleepStatisticsQueryDto(yesterdaySleeps.get(checkIdx).getEndTime()));
			yesterdaySleeps.get(checkIdx).setEndTime(LAST_TIME);
		}

		createdDate = createdDate.minus(1, ChronoUnit.DAYS);

		List<EndTimes> pastSleeps = sleepRepository.findSleepByDay(babyId, createdDate);
		checkIdx = checkTimeDay(pastSleeps);
		if (checkIdx != -1) {
			yesterdaySleeps.add(createSleepStatisticsQueryDto(pastSleeps.get(checkIdx).getEndTime()));
		}

		for (EndTimes et : sleeps)
			sleepDay.add(et);
		Collections.sort(sleepDay, Comparator.comparing(EndTimes::getCreatedTime));
		sleepStatisticsResDto.setTimes(sleepDay);

		// 카드 통계
		Long sleepTime = sumSleepTimeDay(sleeps);
		long cnt = sleeps.size();
		sleepStatisticsResDto.setCnt(cnt);
		sleepStatisticsResDto.setSleepHour(sleepTime / 60);
		sleepStatisticsResDto.setSleepMinute(sleepTime % 60);

		// 수면 횟수 통계 계산
		Long yesterdayCnt = (long)yesterdaySleeps.size();
		Long cntPercent = getPercent(cnt, yesterdayCnt);
		Long yesterdayCntPercent = getPercent(yesterdayCnt, cnt);
		sleepStatisticsResDto.setCntPercent(cntPercent);
		sleepStatisticsResDto.setYesterdayCntPercent(yesterdayCntPercent);

		// 수면 시간 통계 계산
		Long yesterdaySleepTime = sumSleepTimeDay(yesterdaySleeps);
		Long timePercent = getPercent(sleepTime, yesterdaySleepTime);
		Long yesterdayTimePercent = getPercent(yesterdaySleepTime, sleepTime);
		sleepStatisticsResDto.setSleepPercent(timePercent);
		sleepStatisticsResDto.setYesterdaySleepPercent(yesterdayTimePercent);

		return sleepStatisticsResDto;
	}

	/**
	 * 주별 통계 계산
	 */
	@Override
	public SleepStatisticsWeekResDto getSleepStatisticsWeek(Long babyId, LocalDate createdDate) {
		LocalDate startDay = createdDate.minusWeeks(1).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
		startDay = startDay.minusDays(1);
		LocalDate lastDay = createdDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
		SleepStatisticsWeekResDto sleepWeek = new SleepStatisticsWeekResDto();

		// 일주일 데이터 조회
		List<StatisticsTime> sleeps = sleepRepository.findSleepForWeek(babyId, startDay, lastDay);
		
		// 날짜별 종료시간이 시작시간보다 빠른 경우 데이터 정제
		List<Integer> changeIdxes = checkTimeWeek(sleeps);
		for (int i = 0; i < changeIdxes.size(); i++) {
			StatisticsTime changeTime = sleeps.get(changeIdxes.get(i));
			sleeps.add(createStatisticsTime(changeTime.getEndTime(), changeTime.getCreatedDate().plusDays(1)));
			sleeps.get(changeIdxes.get(i)).setEndTime(LAST_TIME);
		}

		startDay = startDay.plusDays(1);
		lastDay = lastDay.plusDays(1);

		// 2주에 관한 관한 map의 key 설정
		Map<LocalDate, SleepStatisticsDayForWeekDto> sleepDay = new HashMap<>();
		while (!startDay.isEqual(lastDay)) {
			sleepDay.put(startDay, new SleepStatisticsDayForWeekDto());
			startDay = startDay.plusDays(1);
		}

		// 해당 날짜가 유효한 경우 Time을 Long으로 바꿔서 넣음
		for (int i = 0; i < sleeps.size(); i++) {
			LocalDate writeDay = sleeps.get(i).getCreatedDate();
			if (sleepDay.containsKey(writeDay)) {
				StatisticsTimeLong writeSleep =
					createStatisticsTimeLong(sleeps.get(i).getCreatedTime(), sleeps.get(i).getEndTime());
				sleepDay.get(writeDay).getSleeps().add(writeSleep);
			}
		}

		// 날짜별 데이터 세팅
		LocalDate writeDay = createdDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
		for (int i = 0; i < WEEK; i++) {
			sleepWeek.getSleepStatistics().put(writeDay.format(DateTimeFormatter.ofPattern("M/dd")), sleepDay.get(writeDay));
			writeDay = writeDay.plusDays(1);
		}
		writeDay = writeDay.minusWeeks(1);

		// 카테고리별 일주일 통계 조회
		Long cnt = sumSleepCnt(sleepDay, writeDay);
		Long sleepTime = sumSleepTimeWeek(sleepDay, writeDay);
		sleepWeek.setCnt(cnt);
		sleepWeek.setSleepHour(sleepTime / 60);
		sleepWeek.setSleepMinute(sleepTime % 60);

		writeDay = writeDay.minusWeeks(1);

		// 수면 횟수 통계 퍼센트 계산
		Long lastWeekCnt = sumSleepCnt(sleepDay, writeDay);
		Long cntPercent = getPercent(cnt, lastWeekCnt);
		Long lastWeekCntPercent = getPercent(lastWeekCnt, cnt);
		sleepWeek.setCntPercent(cntPercent);
		sleepWeek.setYesterdayCntPercent(lastWeekCntPercent);

		// 수면 시간 통계 퍼센트 계산
		Long lastWeekSleepTime = sumSleepTimeWeek(sleepDay, writeDay);
		Long sleepTimePercent = getPercent(sleepTime, lastWeekSleepTime);
		Long lastWeekSleepTimePercent = getPercent(lastWeekSleepTime, sleepTime);
		sleepWeek.setSleepPercent(sleepTimePercent);
		sleepWeek.setYesterdaySleepPercent(lastWeekSleepTimePercent);

		return sleepWeek;
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
	public Long sumSleepTimeDay(List<EndTimes> sleeps) {
		Long sleepTime = 0L;

		for (int i = 0; i < sleeps.size(); i++) {
			long startTime = sleeps.get(i).getCreatedTime().getHour() * 60 + sleeps.get(i).getCreatedTime().getMinute();
			long endTime = sleeps.get(i).getEndTime().getHour() * 60 + sleeps.get(i).getEndTime().getMinute();
			long passTime = endTime - startTime;

			sleepTime += passTime;
		}

		return sleepTime;
	}

	/**
	 * 총 시간 계산 - week
	 */
	public Long sumSleepTimeWeek(Map<LocalDate, SleepStatisticsDayForWeekDto> sleeps, LocalDate writeDay) {
		Long sleepTime = 0L;

		for (int i = 0; i < WEEK; i++) {
			List<StatisticsTimeLong> sleepDay = sleeps.get(writeDay).getSleeps();
			for (int j = 0; j < sleepDay.size(); j++) {
				sleepTime += sleepDay.get(j).getPassTime();
			}

			writeDay = writeDay.plusDays(1);
		}

		return sleepTime;
	}

	/**
	 * 총 횟수 계산
	 */
	public Long sumSleepCnt(Map<LocalDate, SleepStatisticsDayForWeekDto> sleeps, LocalDate writeDay) {
		Long cnt = 0L;

		for (int i = 0; i < WEEK; i++) {
			cnt += sleeps.get(writeDay).getSleeps().size();

			writeDay = writeDay.plusDays(1);
		}

		return cnt;
	}

	/**
	 * 오전 12시 기준으로 이전에 시작해서 이후에 끝나는 기록이 있는지 확인
	 */
	public int checkTimeDay(List<EndTimes> sleeps) {
		for (int i = 0; i < sleeps.size(); i++) {
			if (sleeps.get(i).getEndTime().isBefore(sleeps.get(i).getCreatedTime())) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 오전 12시 기준으로 이전에 시작해서 이후에 끝나는 기록이 있는지 확인
	 */
	public List<Integer> checkTimeWeek(List<StatisticsTime> sleeps) {
		List<Integer> idxList = new ArrayList<>();
		for (int i = 0; i < sleeps.size(); i++) {
			if (sleeps.get(i).getEndTime().isBefore(sleeps.get(i).getCreatedTime())) {
				idxList.add(i);
			}
		}
		return idxList;
	}

	/**
	 * 12시 기준으로 전날에 기록이 존재하면 12시 이후로 추가하기 - Day
	 */
	public EndTimes createSleepStatisticsQueryDto(LocalTime endTime) {
		EndTimes sleepStatisticsQueryDto = new EndTimes();
		sleepStatisticsQueryDto.setCreatedTime(FIRST_TIME);
		sleepStatisticsQueryDto.setEndTime(endTime);

		return sleepStatisticsQueryDto;
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
