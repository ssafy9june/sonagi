package com.fa.sonagi.record.health.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fa.sonagi.record.health.dto.HealthPostDto;
import com.fa.sonagi.record.health.dto.HealthPutDto;
import com.fa.sonagi.record.health.dto.HealthResDto;
import com.fa.sonagi.record.health.entity.Hospital;
import com.fa.sonagi.record.health.repository.HospitalRepository;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HospitalServiceImpl implements HospitalService {

  private final HospitalRepository hospitalRepository;

  /**
   * 병원 기록 아이디로 조회
   */
  @Override
  public HealthResDto findHospitalById(Long id) {
    HealthResDto hospital = hospitalRepository.findHospitalRecord(id);
    return hospital;
  }

  /**
   * 병원 기록 등록
   */
  @Override
  @Transactional
  public HealthResDto registHospital(HealthPostDto healthPostDto) {
    Hospital hospital = Hospital.builder()
        .userId(healthPostDto.getUserId())
        .babyId(healthPostDto.getBabyId())
        .createdDate(healthPostDto.getCreatedDate())
        .createdTime(healthPostDto.getCreatedTime())
        .memo(healthPostDto.getMemo())
        .build();

    hospitalRepository.save(hospital);

    return HealthResDto.builder()
        .healthId(hospital.getId())
        .createdTime(hospital.getCreatedTime())
        .memo(hospital.getMemo())
        .build();
  }

  /**
   * 병원 기록 수정
   */
  @Override
  @Transactional
  public void updateHospital(HealthPutDto healthPutDto) {
    Hospital hospital = hospitalRepository.findById(healthPutDto.getHealthId()).orElseThrow();
    hospital.updateHospital(healthPutDto.getCreatedTime(), healthPutDto.getMemo());
  }

  /**
   * 병원 기록 삭제
   */
  @Override
  @Transactional
  public void deleteHospitalById(Long id) {
    Hospital hospital = hospitalRepository.findById(id).orElseThrow();
    hospitalRepository.delete(hospital);
  }
}
