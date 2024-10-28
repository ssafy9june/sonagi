package com.fa.sonagi.record.pumpingBreast.service;

import com.fa.sonagi.record.pumpingBreast.dto.PumpingBreastPostDto;
import com.fa.sonagi.record.pumpingBreast.dto.PumpingBreastPutDto;
import com.fa.sonagi.record.pumpingBreast.dto.PumpingBreastResDto;

public interface PumpingBreastService {
  PumpingBreastResDto findPumpingBreastById(Long id);

  PumpingBreastResDto registPumpingBreast(PumpingBreastPostDto pumpingBreastPostDto);

  void updatePumpingBreast(PumpingBreastPutDto pumpingBreastPutDto);

  void deletePumpingBreastById(Long id);
}
