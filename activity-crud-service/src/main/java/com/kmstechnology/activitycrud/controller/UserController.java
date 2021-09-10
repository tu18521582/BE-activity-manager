package com.kmstechnology.activitycrud.controller;

import com.kmstechnology.activitycrud.dto.UserDTO;
import com.kmstechnology.activitycrud.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.NoSuchElementException;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(path = "/user")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @GetMapping(path = "/user")
    public String getUserByEmailAndPassword(
            @PathParam("email") String email,
            @PathParam("password") String password) {
//        if(!StringUtils.isBlank(email)) {
//            return email;
////            return userService.getUserByEmailAndPassword(email, password);
//        }
//        throw new NoSuchElementException("Invalid email or password");
        return password;
    }
}
