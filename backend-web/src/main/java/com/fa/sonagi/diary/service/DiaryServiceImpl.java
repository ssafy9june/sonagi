package com.fa.sonagi.diary.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fa.sonagi.FCM.service.FCMNotificationService;
import com.fa.sonagi.baby.dto.CoparentResDto;
import com.fa.sonagi.baby.entity.Baby;
import com.fa.sonagi.baby.repository.BabyRepository;
import com.fa.sonagi.baby.service.BabyService;
import com.fa.sonagi.diary.dto.DiaryPostDto;
import com.fa.sonagi.diary.dto.DiaryPutDto;
import com.fa.sonagi.diary.dto.DiaryResDto;
import com.fa.sonagi.diary.entity.Diary;
import com.fa.sonagi.diary.entity.DiaryFile;
import com.fa.sonagi.diary.repository.DiaryRepository;
import com.fa.sonagi.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DiaryServiceImpl implements DiaryService {
	private final DiaryRepository diaryRepository;
	private final UserRepository userRepository;
	private final BabyRepository babyRepository;
	private final S3File s3File;
	private final BabyService babyService;
	private final FCMNotificationService fcmNotificationService;

	@Override
	public DiaryResDto.DiaryInfo selectByDiaryId(Long diaryId) {
		Optional<Diary> byId = diaryRepository.findById(diaryId);
		Diary diary = byId.orElseThrow();
		DiaryResDto.DiaryInfo diaryInfo = DiaryResDto.DiaryInfo.builder()
		                                                       .diaryId(diary.getId())
		                                                       .content(diary.getContent())
		                                                       .userName(diary.getUserName())
		                                                       .imgUrls(diary.getDiaryFiles()
		                                                                     .stream()
		                                                                     .map((c) -> c.getFileName())
		                                                                     .toList())
		                                                       .writedTime(diary.getCreatedTime())
		                                                       .build();
		return diaryInfo;
	}

	@Override
	@Transactional
	public void createDiary(DiaryPostDto diaryPostDto, List<MultipartFile> imgFiles) throws Exception {
		String userName = userRepository.findById(diaryPostDto.getUserId()).orElseThrow().getName();

		// diary 생성
		Diary diary = Diary.builder()
		                   .babyId(diaryPostDto.getBabyId())
		                   .userName(userName)
		                   .createdDate(diaryPostDto.getWriteDate())
		                   .diaryFiles(new ArrayList<>())
		                   .content(diaryPostDto.getContent())
		                   .createdTime(diaryPostDto.getWriteTime())
		                   .build();

		if(imgFiles != null){
			// file 업로드 + Diary Entity에 일대 다 등록
			imgFiles.stream().map((imgFile) -> {
				try {
					return s3File.upload(imgFile, "img");
				} catch (Exception e) {
					log.error("File UploadError : {}", e.getMessage());
					throw new RuntimeException(e);
				}
			}).forEach((url) -> {
				DiaryFile diaryFile = DiaryFile.builder().fileName(url).build();
				diary.addDiaryFile(diaryFile);
			});
		}
		// Diary repository에 entity build 후 save
		diaryRepository.save(diary);

		Baby baby = babyRepository.findById(diaryPostDto.getBabyId()).orElseThrow();
		baby.updateLastDiaryTime(LocalDateTime.now(ZoneId.of("Asia/Seoul")));
		// 공동양육자를 조회
		List<CoparentResDto> coparents = babyService.findCoparentListByBabyId(baby.getId(), diaryPostDto.getUserId());
		if (coparents.size() > 0) {
			fcmNotificationService.sendWritedDiaryNotification(coparents);
		}
	}

	@Override
	@Transactional
	public void updateDiaryContent(DiaryPutDto diaryPutDto, List<MultipartFile> imgFiles) throws Exception {
		// diary content 수정
		Diary diary = diaryRepository.findById(diaryPutDto.getDiaryId()).orElseThrow();
		diary.updateContent(diaryPutDto.getContent());

		// 새로 사진 추가 및 업로드
		if (imgFiles != null) {
			for (MultipartFile imgFile : imgFiles) {
				String url = s3File.upload(imgFile, "img");
				DiaryFile diaryFile = DiaryFile.builder().fileName(url).build();
				diary.addDiaryFile(diaryFile);
			}
		}

		if (diaryPutDto.getRemoveFiles() != null) {
			// 삭제할 리스트는 삭제
			for (String removeFile : diaryPutDto.getRemoveFiles()) {
				s3File.delete(removeFile);
				List<DiaryFile> list = diary.getDiaryFiles()
				                            .stream()
				                            .filter((diaryFile -> diaryFile.getFileName().equals(removeFile)))
				                            .toList();
				for (int i = 0; i < list.size(); i++) {
					DiaryFile diaryFile = list.get(i);
					diary.removeDiaryFile(diaryFile);
				}
			}
		}

		diaryRepository.saveAndFlush(diary);
	}

	@Override
	@Transactional
	public void deleteDiary(Long diaryId) {
		Diary diary = diaryRepository.findById(diaryId).orElseThrow();
		diaryRepository.delete(diary);
		diaryRepository.flush();
	}

	@Override
	public DiaryResDto.DiaryInfos selectAllByBabyIdAndWriteDay(Long babyId, LocalDate writeDay) throws Exception {
		DiaryResDto.DiaryInfos diaryInfos = new DiaryResDto.DiaryInfos();
		List<DiaryResDto.DiaryInfo> diaryInfoList = new ArrayList<>();

		List<Diary> diaries = diaryRepository.findByBabyIdAndCreatedDate(babyId, writeDay);

		for (Diary diary : diaries) {
			List<String> urlList = diary.getDiaryFiles().stream().map(DiaryFile::getFileName).toList();
			DiaryResDto.DiaryInfo diaryInfo = DiaryResDto.DiaryInfo.builder()
			                                                       .diaryId(diary.getId())
			                                                       .userName(diary.getUserName())
			                                                       .writedTime(diary.getCreatedTime())
			                                                       .imgUrls(urlList)
			                                                       .content(diary.getContent())
			                                                       .build();
			diaryInfoList.add(diaryInfo);
		}
		diaryInfos.setDiaries(diaryInfoList);
		return diaryInfos;
	}

	@Override
	public DiaryResDto.DiaryInfos selectAllByBabyId(Long babyId) throws Exception {
		DiaryResDto.DiaryInfos diaryInfos = new DiaryResDto.DiaryInfos();
		List<DiaryResDto.DiaryInfo> diaryInfoList = new ArrayList<>();

		List<Diary> diaries = diaryRepository.findByBabyId(babyId);

		for (Diary diary : diaries) {
			List<String> urlList = diary.getDiaryFiles().stream().map(DiaryFile::getFileName).toList();
			DiaryResDto.DiaryInfo diaryInfo = DiaryResDto.DiaryInfo.builder()
			                                                       .diaryId(diary.getId())
			                                                       .userName(diary.getUserName())
			                                                       .writedTime(diary.getCreatedTime())
			                                                       .imgUrls(urlList)
			                                                       .content(diary.getContent())
			                                                       .build();
			diaryInfoList.add(diaryInfo);
		}
		diaryInfos.setDiaries(diaryInfoList);
		return diaryInfos;
	}

	@Override
	public List<LocalDate> findAllDiaryByBabyId(Long babyId) {
		List<LocalDate> dateList = new ArrayList<>();

		List<Diary> diaryList = diaryRepository.findByBabyId(babyId);

		for (Diary diary : diaryList) {
			dateList.add(diary.getCreatedDate());
		}
		return dateList;
	}
}
