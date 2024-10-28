package com.fa.sonagi.record.health.service;

import com.fa.sonagi.record.health.dto.FeverPostDto;
import com.fa.sonagi.record.health.dto.FeverPutDto;
import com.fa.sonagi.record.health.dto.FeverResDto;

public interface FeverService {

  FeverResDto findFeverById(Long id);

  FeverResDto registFever(FeverPostDto feverPostDto);

  void updateFever(FeverPutDto feverPutDto);

  void deleteFeverById(Long id);

}