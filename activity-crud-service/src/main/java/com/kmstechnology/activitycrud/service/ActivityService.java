package com.kmstechnology.activitycrud.service;

import com.kmstechnology.activitycrud.dto.ActivityDTO;
import com.kmstechnology.activitycrud.model.Activity;

import java.util.List;
import java.util.Optional;

public interface ActivityService {
    List<ActivityDTO> getAllActivity();
    ActivityDTO getActivityById(Long id);
}
