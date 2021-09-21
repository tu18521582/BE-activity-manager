package com.kmstechnology.activitycrud.service;

import com.kmstechnology.activitycrud.dto.ActivityDTO;
import com.kmstechnology.activitycrud.mapper.ActivityMapper;
import com.kmstechnology.activitycrud.model.Activity;
import com.kmstechnology.activitycrud.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class ActivityServiceImpl implements ActivityService{
    private final ActivityRepository activityRepository;

    @Autowired
    public ActivityServiceImpl(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    @Override
    public List<ActivityDTO> getAllActivity() {
        List<Activity> allActivity = activityRepository.findAll();
        List<ActivityDTO> allActivityDTO = new ArrayList<>();
        for (Activity activity:
                allActivity) {
            allActivityDTO.add(ActivityMapper.toActivityDTO(activity));
        }
        return allActivityDTO;
    }

    @Override
    public ActivityDTO getActivityById(Long id) {
        Activity activity = activityRepository.findActivityById(id)
                .orElseThrow(() -> new NoSuchElementException("Activity not found"));
        return ActivityMapper.toActivityDTO(activity);
    }
}
