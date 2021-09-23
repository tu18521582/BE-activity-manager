package com.kmstechnology.activitycrud.controller;

import com.kmstechnology.activitycrud.dto.UserDTO;
import com.kmstechnology.activitycrud.exception.UnauthorizedException;
import com.kmstechnology.activitycrud.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/user")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        String hash = BCrypt.hashpw(userDTO.getPassword(), BCrypt.gensalt(12));
        userDTO.setPassword(hash);
        return userService.createUser(userDTO);
    }

    @PostMapping(path = "/user/login")
    public UserDTO getUserByEmailAndPassword(@RequestBody UserDTO account) {
        String email = account.getEmail();
        String password = account.getPassword();
        if(!StringUtils.isBlank(email) && !StringUtils.isBlank(password)) {
            return userService.getUserByEmailAndPassword(email, password);
        }
        throw new UnauthorizedException("Invalid username or password");
    }

    @GetMapping(path = "/user")
    public List<UserDTO> getAllUser() {
        return userService.getAllUser();
    }

    @PostMapping(path = "/follow/user/{userId}/activity/{activityId}")
    public void attendActivity(@PathVariable(name = "userId") Long user_id,
                               @PathVariable(name = "activityId") Long activity_id)
    {
        userService.attendActivity(user_id, activity_id);
    }

    @PostMapping(path = "/unfollow/user/{userId}/activity/{activityId}")
    public void unAttendActivity(@PathVariable(name = "userId") Long user_id,
                               @PathVariable(name = "activityId") Long activity_id)
    {
        userService.unAttendActivity(user_id, activity_id);
    }
}
