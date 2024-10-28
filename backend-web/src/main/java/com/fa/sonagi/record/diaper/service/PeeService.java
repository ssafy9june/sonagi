package com.fa.sonagi.record.diaper.service;

import com.fa.sonagi.record.diaper.dto.DiaperPostDto;
import com.fa.sonagi.record.diaper.dto.DiaperPutDto;
import com.fa.sonagi.record.diaper.dto.DiaperResDto;

public interface PeeService {

  DiaperResDto findPeeById(Long id);

  DiaperResDto registPee(DiaperPostDto diaperPostDto);

  void updatePee(DiaperPutDto diaperPutDto);

  void deletePeeById(Long id);
}