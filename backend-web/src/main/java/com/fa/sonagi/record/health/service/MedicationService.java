package com.fa.sonagi.record.health.service;

import com.fa.sonagi.record.health.dto.HealthPostDto;
import com.fa.sonagi.record.health.dto.HealthPutDto;
import com.fa.sonagi.record.health.dto.HealthResDto;

public interface MedicationService {

  HealthResDto findMedicationById(Long id);

  HealthResDto registMedication(HealthPostDto healthPostDto);

  void updateMedication(HealthPutDto healthPutDto);

  void deleteMedicationById(Long id);

}