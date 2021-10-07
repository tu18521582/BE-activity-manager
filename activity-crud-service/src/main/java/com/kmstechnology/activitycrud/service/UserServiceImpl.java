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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ActivityRepository activityRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.activityRepository = activityRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public String createUser(UserDTO userDTO) {
        if (userRepository.existsByEmailOrUsername(userDTO.getEmail(), userDTO.getUsername())) {
            throw new UnauthorizedException("Email or username already exist");
        }
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        User user = User.builder().displayName(userDTO.getDisplayName()).username(userDTO.getUsername())
                .email(userDTO.getEmail()).password(userDTO.getPassword()).build();
        userRepository.save(user);
        return userDTO.getUsername();
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new UnauthorizedException("Invalid username or password"));
        return UserMapper.toLiteUserDTO(user);
    }

    @Override
    public UserDTO getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("User not found"));
        return UserMapper.toLiteUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUser() {
        List<User> userList= userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for(User user: userList){
            userDTOList.add(UserMapper.toUserDTO(user));
        };
        return userDTOList;
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