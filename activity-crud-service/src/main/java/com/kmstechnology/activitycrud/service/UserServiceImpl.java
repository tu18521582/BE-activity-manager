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
        User user = new User(userDTO.getDisplayName(),
                userDTO.getUsername(),
                userDTO.getEmail(),
                userDTO.getPassword());
        userRepository.save(user);
        return userDTO;
    }

    private UserDTO toUserDTO(User user) {
        UserDTO userdto = new UserDTO(
                user.getDisplayName(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword()
        );
        return userdto;
    }
}
