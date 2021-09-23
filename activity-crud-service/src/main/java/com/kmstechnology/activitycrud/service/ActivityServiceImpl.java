package com.kmstechnology.activitycrud.service;

import com.kmstechnology.activitycrud.dto.ActivityDTO;
import com.kmstechnology.activitycrud.dto.UserDTO;
import com.kmstechnology.activitycrud.mapper.ActivityMapper;
import com.kmstechnology.activitycrud.mapper.UserMapper;
import com.kmstechnology.activitycrud.model.Activity;
import com.kmstechnology.activitycrud.model.User;
import com.kmstechnology.activitycrud.repository.ActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@Transactional
public class ActivityServiceImpl implements ActivityService{
    private final ActivityRepository activityRepository;
    private final UserService userService;

    @Autowired
    public ActivityServiceImpl(ActivityRepository activityRepository, UserService userService) {
        this.activityRepository = activityRepository;
        this.userService = userService;
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

    @Override
    public void createActivity(ActivityDTO activityDTO, Long userid) {
        Activity newActivity = Activity.builder().title(activityDTO.getTitle()).category(activityDTO.getCategory())
                .description(activityDTO.getDescription()).date(activityDTO.getDate())
                .time(activityDTO.getTime()).venue(activityDTO.getVenue()).city(activityDTO.getCity())
                .build();
        UserDTO hostUser = userService.getUserLiteById(userid);
        newActivity.setUser(UserMapper.toLiteUser(hostUser));
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

    @Override
    public Set<ActivityDTO> getActivityUserHosted(Long user_id) {
        return userService.getUserById(user_id).getActivities();
    }

    @Override
    public Set<ActivityDTO> getActivityUserAttend(Long user_id) {
        return userService.getUserById(user_id).getActivityAttend();
    }
}
