package com.fa.sonagi.record.diaper.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fa.sonagi.record.diaper.dto.DiaperPostDto;
import com.fa.sonagi.record.diaper.dto.DiaperPutDto;
import com.fa.sonagi.record.diaper.dto.DiaperResDto;
import com.fa.sonagi.record.diaper.entity.Pee;
import com.fa.sonagi.record.diaper.repository.PeeRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PeeServiceImpl implements PeeService {

  private final PeeRepository peeRepository;

  /**
   * 소변 기록 아이디로 조회
   */
  @Override
  public DiaperResDto findPeeById(Long id) {
    DiaperResDto pee = peeRepository.findPeeRecord(id);
    return pee;
  }

  /**
   * 소변 기록 등록
   */
  @Override
  @Transactional
  public DiaperResDto registPee(DiaperPostDto diaperPostDto) {
    Pee pee = Pee.builder()
        .userId(diaperPostDto.getUserId())
        .babyId(diaperPostDto.getBabyId())
        .createdDate(diaperPostDto.getCreatedDate())
        .createdTime(diaperPostDto.getCreatedTime())
        .memo(diaperPostDto.getMemo())
        .build();

    peeRepository.save(pee);

    return DiaperResDto.builder()
        .diaperId(pee.getId())
        .createdTime(pee.getCreatedTime())
        .memo(pee.getMemo())
        .build();
  }

  /**
   * 소변 기록 수정
   */
  @Override
  @Transactional
  public void updatePee(DiaperPutDto diaperPutDto) {
    Pee pee = peeRepository.findById(diaperPutDto.getDiaperId()).orElseThrow();

    pee.updatePee(diaperPutDto.getCreatedTime(), diaperPutDto.getMemo());
  }

  /**
   * 소변 기록 삭제
   */
  @Override
  @Transactional
  public void deletePeeById(Long id) {
    Pee pee = peeRepository.findById(id).orElseThrow();

    peeRepository.delete(pee);
  }
}