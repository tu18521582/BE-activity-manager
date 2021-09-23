package com.kmstechnology.activitycrud.mapper;

import com.kmstechnology.activitycrud.dto.UserDTO;
import com.kmstechnology.activitycrud.model.User;

import java.util.stream.Collectors;

public class UserMapper {
    public static UserDTO toLiteUserDTO(User user) {
        return UserDTO.builder().id(user.getId()).displayName(user.getDisplayName()).username(user.getUsername())
                .email(user.getEmail()).build();
    }

    public static UserDTO toUserDTO(User user) {
        return UserDTO.builder().id(user.getId()).displayName(user.getDisplayName()).username(user.getUsername())
                .email(user.getEmail()).password(user.getPassword())
                .activityAttend(user.getActivityAttend().stream().map(ActivityMapper::toLiteActivityDTO).collect(Collectors.toSet()))
                .activities(user.getActivities().stream().map(ActivityMapper::toLiteActivityDTO).collect(Collectors.toSet())).build();
    }

    public static User toLiteUser(UserDTO userDTO) {
        return User.builder().id(userDTO.getId()).displayName(userDTO.getDisplayName()).username(userDTO.getUsername())
                .email(userDTO.getEmail()).password(userDTO.getPassword()).build();
    }
}
