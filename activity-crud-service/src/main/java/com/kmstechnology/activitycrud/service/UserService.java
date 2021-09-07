package com.kmstechnology.activitycrud.service;

import com.kmstechnology.activitycrud.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);

    UserDto getUserById(Long id);

    UserDto getUserByUsername(String username);

    UserDto getUserByEmail(String email);
}
