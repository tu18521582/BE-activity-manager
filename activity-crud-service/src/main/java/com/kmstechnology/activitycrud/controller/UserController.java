package com.kmstechnology.activitycrud.controller;

import com.auth0.jwt.JWT;
import com.kmstechnology.activitycrud.dto.UserDTO;
import com.kmstechnology.activitycrud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/user")
    public UserDTO getUserInformation(Principal principal){
        String email = principal.getName();
        return userService.getUserByEmail(email);
    }

    @PostMapping(path = "/user")
    public ResponseEntity<String> createUser(@RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(userService.createUser(userDTO), HttpStatus.OK);
    }

    @GetMapping(path = "/users")
    public List<UserDTO> getAllUser() {
        return userService.getAllUser();
    }

    @PostMapping(path = "/follow/activity/{activityId}")
    public void attendActivity(@PathVariable(name = "activityId") Long activityId,
            @RequestHeader("Authorization") String token)
    {
        Long userId = Long.parseLong(JWT.decode(token.substring(7)).getClaim("id").toString());
        userService.attendActivity(userId, activityId);
    }

    @PostMapping(path = "/unfollow/activity/{activityId}")
    public void unAttendActivity(@PathVariable(name = "activityId") Long activityId,
            @RequestHeader("Authorization") String token)
    {
        Long userId = Long.parseLong(JWT.decode(token.substring(7)).getClaim("id").toString());
        userService.unAttendActivity(userId, activityId);
    }
}
