package com.fa.sonagi.record.activity.service;

import com.fa.sonagi.record.activity.dto.ActivityPostDto;
import com.fa.sonagi.record.activity.dto.ActivityPutDto;
import com.fa.sonagi.record.activity.dto.ActivityResDto;

public interface PlayServeice {

  ActivityResDto findPlayById(Long id);

  ActivityResDto registPlay(ActivityPostDto activityPostDto);

  void updatePlay(ActivityPutDto activityPutDto);

  void deletePlay(Long id);
}
