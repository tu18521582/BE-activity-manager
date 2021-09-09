package com.kmstechnology.activitycrud.service;

import com.kmstechnology.activitycrud.dto.UserDTO;
import com.kmstechnology.activitycrud.model.User;
import com.kmstechnology.activitycrud.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        User user = User.builder().displayName(userDTO.getDisplayName()).username(userDTO.getUsername())
                .email(userDTO.getEmail()).password(userDTO.getPassword()).build();
        userRepository.save(user);
        return userDTO;
    }

    private UserDTO toUserDTO(User user) {
        return UserDTO.builder().displayName(user.getDisplayName()).username(user.getUsername())
                .email(user.getEmail()).password(user.getPassword()).build();
    }
}
