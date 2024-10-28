package com.fa.sonagi.record.diaper.service;

import com.fa.sonagi.record.diaper.dto.DiaperPostDto;
import com.fa.sonagi.record.diaper.dto.DiaperPutDto;
import com.fa.sonagi.record.diaper.dto.DiaperResDto;

public interface PoopService {

  DiaperResDto findPoopById(Long id);

  DiaperResDto registPoop(DiaperPostDto diaperPostDto);

  void updatePoop(DiaperPutDto diaperPutDto);

  void deletePoopById(Long id);
}