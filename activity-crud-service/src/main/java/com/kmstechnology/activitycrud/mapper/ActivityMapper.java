package com.kmstechnology.activitycrud.mapper;

import com.kmstechnology.activitycrud.dto.ActivityDTO;
import com.kmstechnology.activitycrud.dto.UserDTO;
import com.kmstechnology.activitycrud.model.Activity;

import java.util.stream.Collectors;

public class ActivityMapper {
    public static ActivityDTO toActivityDTO(Activity activity) {
        return ActivityDTO.builder().id(activity.getId()).title(activity.getTitle())
                .category(activity.getCategory())
                .description(activity.getDescription()).date(activity.getDate())
                .time(activity.getTime()).venue(activity.getVenue()).city(activity.getCity())
                .host(UserMapper.toLiteUserDTO(activity.getUser()))
                .userAttend(activity.getUserAttend()
                        .stream().map(UserMapper::toLiteUserDTO).collect(Collectors.toSet()))
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
