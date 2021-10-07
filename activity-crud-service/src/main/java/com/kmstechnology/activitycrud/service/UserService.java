package com.kmstechnology.activitycrud.service;

import com.kmstechnology.activitycrud.dto.UserDTO;
import com.kmstechnology.activitycrud.model.User;

import java.util.List;

public interface UserService {
    String createUser(UserDTO userDTO);
    UserDTO getUserById(Long id);
    UserDTO getUserByEmail(String email);
    List<UserDTO> getAllUser();
    void attendActivity(Long user_id, Long activity_id);
    void unAttendActivity(Long user_id, Long activity_id);
}
