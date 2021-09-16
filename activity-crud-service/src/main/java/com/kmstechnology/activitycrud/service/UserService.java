package com.kmstechnology.activitycrud.service;

import com.kmstechnology.activitycrud.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserByEmailAndPassword(String email, String password);
    List<UserDTO> getAllUser();
    void attendActivity(Long user_id, Long activity_id);
}
