package com.fa.sonagi.record.pumpingBreast.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fa.sonagi.record.pumpingBreast.dto.PumpingBreastPostDto;
import com.fa.sonagi.record.pumpingBreast.dto.PumpingBreastPutDto;
import com.fa.sonagi.record.pumpingBreast.dto.PumpingBreastResDto;
import com.fa.sonagi.record.pumpingBreast.entity.PumpingBreast;
import com.fa.sonagi.record.pumpingBreast.repository.PumpingBreastRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PumpingBreastServiceImpl implements PumpingBreastService {

  private final PumpingBreastRepository pumpingBreastRepository;

  /**
   * 유축 기록 아이디로 조회
   */
  @Override
  public PumpingBreastResDto findPumpingBreastById(Long id) {
    return pumpingBreastRepository.findPumpingBreastRecord(id);
  }

  /**
   * 유축 기록 등록
   */
  @Override
  @Transactional
  public PumpingBreastResDto registPumpingBreast(PumpingBreastPostDto pumpingBreastPostDto) {
    PumpingBreast pumpingBreast = PumpingBreast.builder()
        .userId(pumpingBreastPostDto.getUserId())
        .babyId(pumpingBreastPostDto.getBabyId())
        .createdTime(pumpingBreastPostDto.getCreatedTime())
        .createdDate(pumpingBreastPostDto.getCreatedDate())
        .amount(pumpingBreastPostDto.getAmount())
        .memo(pumpingBreastPostDto.getMemo())
        .build();

    pumpingBreastRepository.save(pumpingBreast);

    return PumpingBreastResDto.builder()
        .pumpingBreastId(pumpingBreast.getId())
        .amount(pumpingBreast.getAmount())
        .memo(pumpingBreast.getMemo())
        .createdTime(pumpingBreast.getCreatedTime())
        .build();
  }

  /**
   * 유축 기록 수정
   */
  @Override
  @Transactional
  public void updatePumpingBreast(PumpingBreastPutDto pumpingBreastPutDto) {
    PumpingBreast pumpingBreast = pumpingBreastRepository.findById(pumpingBreastPutDto.getPumpingBreastId()).orElseThrow();

    pumpingBreast.updatePumpingBreast(pumpingBreastPutDto.getAmount(), pumpingBreastPutDto.getMemo(), pumpingBreastPutDto.getCreatedTime());
  }

  /**
   * 유축 기록 삭제
   */
  @Override
  @Transactional
  public void deletePumpingBreastById(Long id) {
    PumpingBreast pumpingBreast = pumpingBreastRepository.findById(id).orElseThrow();

    pumpingBreastRepository.delete(pumpingBreast);
  }
}
