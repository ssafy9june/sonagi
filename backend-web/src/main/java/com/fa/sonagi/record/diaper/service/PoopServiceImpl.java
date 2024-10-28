package com.fa.sonagi.record.diaper.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fa.sonagi.record.diaper.dto.DiaperPostDto;
import com.fa.sonagi.record.diaper.dto.DiaperPutDto;
import com.fa.sonagi.record.diaper.dto.DiaperResDto;
import com.fa.sonagi.record.diaper.entity.Poop;
import com.fa.sonagi.record.diaper.repository.PoopRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PoopServiceImpl implements PoopService {

  private final PoopRepository poopRepository;

  /**
   * 대변 기록 아이디로 조회
   */
  @Override
  public DiaperResDto findPoopById(Long id) {
    DiaperResDto poop = poopRepository.findPoopRecord(id);

    return poop;
  }

  /**
   * 대변 기록 등록
   */
  @Override
  @Transactional
  public DiaperResDto registPoop(DiaperPostDto diaperPostDto) {
    Poop poop = Poop.builder()
        .userId(diaperPostDto.getUserId())
        .babyId(diaperPostDto.getBabyId())
        .createdDate(diaperPostDto.getCreatedDate())
        .createdTime(diaperPostDto.getCreatedTime())
        .memo(diaperPostDto.getMemo())
        .build();

    poopRepository.save(poop);

    return DiaperResDto.builder()
        .diaperId(poop.getId())
        .createdTime(poop.getCreatedTime())
        .memo(poop.getMemo())
        .build();
  }

  /**
   * 대변 기록 수정
   */
  @Override
  @Transactional
  public void updatePoop(DiaperPutDto diaperPutDto) {
    Poop poop = poopRepository.findById(diaperPutDto.getDiaperId()).orElseThrow();

    poop.updatePoop(diaperPutDto.getCreatedTime(), diaperPutDto.getMemo());
  }

  /**
   * 대변 기록 삭제
   */
  @Override
  @Transactional
  public void deletePoopById(Long id) {
    Poop poop = poopRepository.findById(id).orElseThrow();

    poopRepository.delete(poop);
  }

}