package com.kmstechnology.activitycrud.controller;

import com.kmstechnology.activitycrud.dto.UserDTO;
import com.kmstechnology.activitycrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @CrossOrigin(origins = "http://localhost:8081")
    public String hello() {
        return "Hello";
    }

    @PostMapping(path = "/user")
    @CrossOrigin(origins = "http://localhost:8081")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }
}
