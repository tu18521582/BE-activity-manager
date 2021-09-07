package com.kmstechnology.activitycrud.controller;

import com.kmstechnology.activitycrud.dto.UserDto;
import com.kmstechnology.activitycrud.service.UserService;
import java.util.NoSuchElementException;
import javax.websocket.server.PathParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public UserDto createUser(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @GetMapping("/user/{id}")
    public UserDto getUserById(@PathVariable("id") Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/user")
    public UserDto getUserByUserName(@PathParam("username") String username, @PathParam("email") String email) {
        if (!StringUtils.isBlank(username)) {
            return userService.getUserByUsername(username);
        }
        if (!StringUtils.isBlank(email)) {
            return userService.getUserByEmail(email);
        }
        throw new NoSuchElementException("invalid request");
    }
}
