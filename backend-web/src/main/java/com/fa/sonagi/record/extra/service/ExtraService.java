package com.fa.sonagi.record.extra.service;

import com.fa.sonagi.record.extra.dto.ExtraPostDto;
import com.fa.sonagi.record.extra.dto.ExtraPutDto;
import com.fa.sonagi.record.extra.dto.ExtraResDto;

public interface ExtraService {

  ExtraResDto findExtraById(Long id);

  ExtraResDto registExtra(ExtraPostDto extraPostDto);

  void updateExtra(ExtraPutDto extraPutDto);

  void deleteExtra(Long id);
}
