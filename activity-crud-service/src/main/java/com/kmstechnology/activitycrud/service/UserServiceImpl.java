package com.kmstechnology.activitycrud.service;

import com.kmstechnology.activitycrud.dto.UserDto;
import com.kmstechnology.activitycrud.model.User;
import com.kmstechnology.activitycrud.repository.UserRepository;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepo;

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = User.builder().username(userDto.getUsername()).firstName(userDto.getFirstName()).lastName(userDto.getLastName()).email(userDto.getEmail()).build();
        userRepo.save(user);
        return userDto;
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepo.findById(id).orElseThrow(NoSuchElementException::new);
        return toUserDto(user);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        User user = userRepo.findByUsername(username).orElseThrow(NoSuchElementException::new);
        return toUserDto(user);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepo.findByEmail(email).orElseThrow(NoSuchElementException::new);
        return toUserDto(user);
    }

    private UserDto toUserDto(User user) {
        return UserDto.builder().username(user.getUsername()).firstName(user.getFirstName()).lastName(user.getLastName()).email(user.getEmail()).build();
    }
}
