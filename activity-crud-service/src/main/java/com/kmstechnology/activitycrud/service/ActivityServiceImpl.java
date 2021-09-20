package com.kmstechnology.activitycrud.service;

import com.kmstechnology.activitycrud.dto.ActivityDTO;
import com.kmstechnology.activitycrud.dto.UserDTO;
import com.kmstechnology.activitycrud.model.Activity;
import com.kmstechnology.activitycrud.model.User;
import com.kmstechnology.activitycrud.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

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
            allActivityDTO.add(toActivityDTO(activity));
        }
        return allActivityDTO;
    }

    @Override
    public ActivityDTO getActivityById(Long id) {
        Activity activity = activityRepository.findActivityById(id).orElseThrow(NoSuchElementException::new);
        return toActivityDTO(activity);
    }

    private UserDTO toUserDTO(User user) {
        return UserDTO.builder().id(user.getId()).displayName(user.getDisplayName()).username(user.getUsername())
                .email(user.getEmail()).build();
    }

    private ActivityDTO toActivityDTO(Activity activity) {
        return ActivityDTO.builder().id(activity.getId()).title(activity.getTitle())
                .category(activity.getCategory())
                .description(activity.getDescription()).date(activity.getDate())
                .time(activity.getTime()).venue(activity.getVenue()).city(activity.getCity())
                .host(toUserDTO(activity.getUser()))
                .userAttend(activity.getUserAttend()
                        .stream().map(this::toUserDTO).collect(Collectors.toSet()))
                .build();
    }

    public static ActivityDTO toLiteActivityDTO(Activity activity) {
        return ActivityDTO.builder().id(activity.getId()).title(activity.getTitle())
                .category(activity.getCategory())
                .description(activity.getDescription()).date(activity.getDate())
                .time(activity.getTime()).venue(activity.getVenue()).city(activity.getCity())
                .host(UserDTO.builder().id(activity.getUser().getId()).build())
                .userAttend(activity.getUserAttend()
                        .stream().map(user -> UserDTO.builder().id(user.getId()).build()).collect(Collectors.toSet()))
                .build();
    }
}
