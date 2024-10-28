package com.fa.sonagi.record.activity.service;

import com.fa.sonagi.record.activity.dto.ActivityPostDto;
import com.fa.sonagi.record.activity.dto.ActivityPutDto;
import com.fa.sonagi.record.activity.dto.ActivityResDto;

public interface TummytimeService {

  ActivityResDto findTummytimeById(Long id);

  ActivityResDto registTummytime(ActivityPostDto activityPostDto);

  void updateTummytime(ActivityPutDto activityPutDto);

  void deleteTummytime(Long id);
}
