package com.kmstechnology.activitycrud.service;

import com.kmstechnology.activitycrud.dto.UserDTO;
import com.kmstechnology.activitycrud.exception.UnauthorizedException;
import com.kmstechnology.activitycrud.model.User;
import com.kmstechnology.activitycrud.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
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
        if (userRepository.existsByEmailOrUsername(userDTO.getEmail(), userDTO.getUsername())) {
            throw new UnauthorizedException("Email or username already exist");
        }
        User user = User.builder().displayName(userDTO.getDisplayName()).username(userDTO.getUsername())
                .email(userDTO.getEmail()).password(userDTO.getPassword()).build();
        userRepository.save(user);
        return userDTO;
    }

    @Override
    public UserDTO getUserByEmailAndPassword(String email, String password) {
        User user = userRepository.findByEmail(email).orElseThrow(()->{
            return new UnauthorizedException("Invalid username or password");
        });
        if(BCrypt.checkpw(password, user.getPassword())) {
            return toUserDTO(user);
        }
        throw new UnauthorizedException("Invalid username or password");
    }

    private UserDTO toUserDTO(User user) {
        return UserDTO.builder().id(user.getId()).displayName(user.getDisplayName()).username(user.getUsername())
                .email(user.getEmail()).password(user.getPassword()).build();
    }
}
