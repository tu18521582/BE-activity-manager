package com.kmstechnology.activitycrud.controller;


import com.kmstechnology.activitycrud.dto.ActivityDTO;
import com.kmstechnology.activitycrud.dto.UserDTO;
import com.kmstechnology.activitycrud.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Set;

@RestController
public class ActivityController {

    private final ActivityService activityService;

    @Autowired
    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping(path = "/activities")
    public List<ActivityDTO> getAllActivity() {
        return activityService.getAllActivity();
    }

    @GetMapping(path = "/activity/{id}")
    public ActivityDTO getActivityById(@PathVariable(name = "id") Long id) {
        return activityService.getActivityById(id);
    }

    @PostMapping(path = "/activity")
    public void createActivity(@RequestBody ActivityDTO activityDTO, @PathParam("userid") Long userid) {
        activityService.createActivity(activityDTO, userid);
    }

    @PutMapping(path = "/activity/{id}")
    public void updateActivity(@RequestBody ActivityDTO activityDTO) {
        activityService.updateActivity(activityDTO);
    }


    @GetMapping(path = "/activityhost/{userid}")
    public Set<ActivityDTO> getActivityUserHosted(@PathVariable(name= "userid") Long user_id) {
        return activityService.getActivityUserHosted(user_id);
    }

    @GetMapping(path = "/activityattend/{userid}")
    public Set<ActivityDTO> getActivityUserAttend(@PathVariable(name= "userid") Long user_id) {
        return activityService.getActivityUserAttend(user_id);
    }
}
