package com.kmstechnology.activitycrud.service;

import com.kmstechnology.activitycrud.dto.ActivityDTO;

import java.util.List;

public interface ActivityService {
    List<ActivityDTO> getAllActivity();
    ActivityDTO getActivityById(Long id);
    void createActivity(ActivityDTO activityDTO, Long userid);
    void updateActivity(ActivityDTO activityDTO);
}
