package com.codegym.web_service.controller;

import com.codegym.dao.entity.UserProfile;
import com.codegym.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "Authorization")
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserProfileService userProfileService;


    //Get info to idUserProfile
    @GetMapping("/{id}")
    public ResponseEntity<?>getAllInfoUser(@PathVariable("id") Long id) {
       UserProfile userProfiles=userProfileService.findAllProfileUser(id);
        System.out.println(userProfiles);
        return new ResponseEntity<>(userProfiles, HttpStatus.OK);
    }

    //Update info
    @PutMapping("/update/{id}" )
    public ResponseEntity<UserProfile> editUserProfile( @RequestBody UserProfile userProfile, @PathVariable("id") long id) {
        UserProfile userProfiles=userProfileService.findById(id);
        if (userProfiles == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<UserProfile>(HttpStatus.NOT_FOUND);
        }
        userProfiles.setFullName(userProfile.getFullName());
        userProfiles.setAddress(userProfile.getAddress());
        userProfiles.setDayOfBirth(userProfile.getDayOfBirth());
        userProfiles.setIdentityNumber(userProfile.getIdentityNumber());
        userProfiles.setPhone(userProfile.getPhone());
        userProfiles.setEmail(userProfile.getEmail());
        userProfiles.setId(userProfile.getId());

        userProfileService.editUserProfile(userProfiles);
        return new ResponseEntity<UserProfile>(userProfiles, HttpStatus.OK);

    }


}
