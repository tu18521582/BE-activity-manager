package com.kmstechnology.activitycrud.service;

import com.kmstechnology.activitycrud.dto.ActivityDTO;
import com.kmstechnology.activitycrud.dto.UserDTO;
import com.kmstechnology.activitycrud.exception.UnauthorizedException;
import com.kmstechnology.activitycrud.model.Activity;
import com.kmstechnology.activitycrud.model.User;
import com.kmstechnology.activitycrud.repository.ActivityRepository;
import com.kmstechnology.activitycrud.repository.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


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
        User user = userRepository.findByEmailAndPassword(email, password).orElseThrow(()->{
            return new UnauthorizedException("Invalid username or password");
        });
        return toUserDTO(user);
    }

    @Override
    public List<UserDTO> getAllUser() {
        List<User> userList= userRepository.findAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        for(User user: userList){
            userDTOList.add(toUserDTO(user));
        };
        return userDTOList;
    }

    @Override
    public void attendActivity(Long user_id, Long activity_id) {
        Activity activity = activityRepository.findActivityById(activity_id)
                .orElseThrow(NoSuchElementException::new);
        User user = userRepository.findById(user_id)
                .orElseThrow(NoSuchElementException::new);
        user.getActivityAttend().add(activity);
        userRepository.save(user);
    }

    @Override
    public void unAttendActivity(Long user_id, Long activity_id) {
        Activity activity = activityRepository.findActivityById(activity_id)
                .orElseThrow(NoSuchElementException::new);
        User user = userRepository.findById(user_id)
                .orElseThrow(NoSuchElementException::new);
        user.getActivityAttend().remove(activity);
        userRepository.save(user);
    }

    private UserDTO toUserDTO(User user) {
        Hibernate.initialize(user.getActivities());
        Hibernate.initialize(user.getActivityAttend());
        return UserDTO.builder().id(user.getId()).displayName(user.getDisplayName()).username(user.getUsername())
                .email(user.getEmail()).password(user.getPassword()).activityAttend(user.getActivityAttend())
                .activities(user.getActivities()).build();
    }
}
