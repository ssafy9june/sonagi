package com.fa.sonagi.record.health.service;

import com.fa.sonagi.record.health.dto.HealthPostDto;
import com.fa.sonagi.record.health.dto.HealthPutDto;
import com.fa.sonagi.record.health.dto.HealthResDto;

public interface HospitalService {

  HealthResDto findHospitalById(Long id);

  HealthResDto registHospital(HealthPostDto healthPostDto);

  void updateHospital(HealthPutDto healthPutDto);

  void deleteHospitalById(Long id);
}
