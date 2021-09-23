package com.kmstechnology.activitycrud.service;

import com.kmstechnology.activitycrud.dto.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO userDTO);
    UserDTO getUserByEmailAndPassword(String email, String password);
    UserDTO getUserById(Long id);
    List<UserDTO> getAllUser();
    void attendActivity(Long user_id, Long activity_id);
    void unAttendActivity(Long user_id, Long activity_id);
}
