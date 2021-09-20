package com.kmstechnology.activitycrud.service;

import com.kmstechnology.activitycrud.dto.ActivityDTO;
import com.kmstechnology.activitycrud.dto.UserDTO;
import com.kmstechnology.activitycrud.model.Activity;
import com.kmstechnology.activitycrud.model.User;
import com.kmstechnology.activitycrud.repository.ActivityRepository;
import com.kmstechnology.activitycrud.repository.UserRepository;
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
    private final UserRepository userRepository;

    @Autowired
    public ActivityServiceImpl(ActivityRepository activityRepository, UserRepository userRepository) {
        this.activityRepository = activityRepository;
        this.userRepository = userRepository;
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

    @Override
    public void createActivity(ActivityDTO activityDTO, Long userid) {
        Activity newActivity = Activity.builder().title(activityDTO.getTitle()).category(activityDTO.getCategory())
                .description(activityDTO.getDescription()).date(activityDTO.getDate())
                .time(activityDTO.getTime()).venue(activityDTO.getVenue()).city(activityDTO.getCity())
                .build();
        User hostUser = userRepository.getById(userid);
        newActivity.setUser(hostUser);
        activityRepository.save(newActivity);
    }

    @Override
    public void updateActivity(ActivityDTO activityDTO) {
        Activity activityToUpdate = activityRepository.findActivityById(activityDTO.getId())
                .orElseThrow(NoSuchElementException::new);
        activityToUpdate.setTitle(activityDTO.getTitle());
        activityToUpdate.setDescription(activityDTO.getDescription());
        activityToUpdate.setCategory(activityDTO.getCategory());
        activityToUpdate.setDate(activityDTO.getDate());
        activityToUpdate.setTime(activityDTO.getTime());
        activityToUpdate.setVenue(activityDTO.getVenue());
        activityToUpdate.setCity(activityDTO.getCity());
        activityRepository.save(activityToUpdate);
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
