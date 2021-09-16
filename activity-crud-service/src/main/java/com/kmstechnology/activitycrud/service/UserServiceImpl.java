package com.kmstechnology.activitycrud.service;

import com.kmstechnology.activitycrud.dto.UserDTO;
import com.kmstechnology.activitycrud.exception.UnauthorizedException;
import com.kmstechnology.activitycrud.model.User;
import com.kmstechnology.activitycrud.repository.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
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
            Hibernate.initialize(user.getActivities());
            Hibernate.initialize(user.getActivityAttend());
            userDTOList.add(toUserDTO(user));
        };
        return userDTOList;
    }

    @Override
    public void attendActivity(Long user_id, Long activity_id) {
    }

    private UserDTO toUserDTO(User user) {
        return UserDTO.builder().id(user.getId()).displayName(user.getDisplayName()).username(user.getUsername())
                .email(user.getEmail()).password(user.getPassword()).activityAttend(user.getActivityAttend())
                .activities(user.getActivities()).build();
    }
}
