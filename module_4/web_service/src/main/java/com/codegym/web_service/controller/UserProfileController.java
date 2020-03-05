package com.codegym.web_service.controller;

import com.codegym.dao.entity.UserProfile;
import com.codegym.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "Authorization")
@RequestMapping("user")
public class UserProfileController {

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("/{id}")
    public ResponseEntity<?>getAllInfoUser(@PathVariable("id") Long id) {
       UserProfile userProfiles=userProfileService.findAllProfileUser(id);
        System.out.println(userProfiles);
        return new ResponseEntity<>(userProfiles, HttpStatus.OK);
    }


}
