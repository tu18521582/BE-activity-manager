package com.kmstechnology.activitycrud.service;

import com.kmstechnology.activitycrud.dto.UserDTO;
import com.kmstechnology.activitycrud.exception.UnauthorizedException;
import com.kmstechnology.activitycrud.mapper.UserMapper;
import com.kmstechnology.activitycrud.model.Activity;
import com.kmstechnology.activitycrud.model.User;
import com.kmstechnology.activitycrud.repository.ActivityRepository;
import com.kmstechnology.activitycrud.repository.UserRepository;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Service
@Transactional
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final ActivityRepository activityRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ActivityRepository activityRepository) {
        this.userRepository = userRepository;
        this.activityRepository = activityRepository;
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

    @Override
    public void attendActivity(Long user_id, Long activity_id) {
        Activity activity = activityRepository.findActivityById(activity_id)
                .orElseThrow(() -> new NoSuchElementException("Activity not found"));
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        user.getActivityAttend().add(activity);
        userRepository.save(user);
    }

    @Override
    public void unAttendActivity(Long user_id, Long activity_id) {
        Activity activity = activityRepository.findActivityById(activity_id)
                .orElseThrow(() -> new NoSuchElementException("Activity not found"));
        User user = userRepository.findById(user_id)
                .orElseThrow(() -> new NoSuchElementException("User not found"));
        user.getActivityAttend().remove(activity);
        userRepository.save(user);
    }
}