package com.fa.sonagi.record.health.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fa.sonagi.record.health.dto.FeverPostDto;
import com.fa.sonagi.record.health.dto.FeverPutDto;
import com.fa.sonagi.record.health.dto.FeverResDto;
import com.fa.sonagi.record.health.entity.Fever;
import com.fa.sonagi.record.health.repository.FeverRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FeverServiceImpl implements FeverService {

  private final FeverRepository feverRepository;

  /**
   * 체온 기록 아이디로 조회
   */
  @Override
  public FeverResDto findFeverById(Long id) {
    FeverResDto fever = feverRepository.findFeverRecord(id);
    return fever;
  }

  /**
   * 체온 기록 등록
   */
  @Override
  @Transactional
  public FeverResDto registFever(FeverPostDto feverPostDto) {
    Fever fever = Fever.builder()
        .userId(feverPostDto.getUserId())
        .babyId(feverPostDto.getBabyId())
        .createdDate(feverPostDto.getCreatedDate())
        .createdTime(feverPostDto.getCreatedTime())
        .bodyTemperature(feverPostDto.getBodyTemperature())
        .memo(feverPostDto.getMemo())
        .build();

    feverRepository.save(fever);

    return FeverResDto.builder()
        .healthId(fever.getId())
        .createdTime(fever.getCreatedTime())
        .bodyTemperature(fever.getBodyTemperature())
        .memo(fever.getMemo())
        .build();
  }

  /**
   * 체온 기록 수정
   */
  @Override
  @Transactional
  public void updateFever(FeverPutDto feverPutDto) {
    Fever fever = feverRepository.findById(feverPutDto.getHealthId()).orElseThrow();
    fever.updateFever(feverPutDto.getCreatedTime(), feverPutDto.getBodyTemperature(),
        feverPutDto.getMemo());
  }

  /**
   * 체온 기록 삭제
   */
  @Override
  @Transactional
  public void deleteFeverById(Long id) {
    Fever fever = feverRepository.findById(id).orElseThrow();
    feverRepository.delete(fever);
  }
}