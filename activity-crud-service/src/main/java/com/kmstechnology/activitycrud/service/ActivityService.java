package com.kmstechnology.activitycrud.service;

import com.kmstechnology.activitycrud.dto.ActivityDTO;
import com.kmstechnology.activitycrud.dto.UserDTO;

import java.util.List;
import java.util.Set;

public interface ActivityService {
    List<ActivityDTO> getAllActivity();
    ActivityDTO getActivityById(Long id);
    void createActivity(ActivityDTO activityDTO, Long userid);
    void updateActivity(ActivityDTO activityDTO);
    Set<ActivityDTO> getActivityUserHosted(Long user_id);
    Set<ActivityDTO> getActivityUserAttend(Long user_id);

}
