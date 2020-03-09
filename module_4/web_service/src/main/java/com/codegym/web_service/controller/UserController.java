package com.codegym.web_service.controller;

import com.codegym.dao.DTO.UserDTO;
import com.codegym.dao.DTO.UserRegisterDTO;
import com.codegym.dao.entity.User;
import com.codegym.dao.entity.UserProfile;
import com.codegym.dao.entity.UserRank;
import com.codegym.service.RoleService;
import com.codegym.service.UserProfileService;
import com.codegym.service.UserRankService;
import com.codegym.service.ipml.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "Authorization")
@RequestMapping("")
public class UserController {
    @Autowired
    UserRankService userRankService;
    @Autowired
    RoleService roleService;
    @Autowired
    UserServiceImpl userService;
    @Autowired
    UserProfileService userProfileService;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public  ResponseEntity<?> saveUser(@RequestBody UserRegisterDTO userRegisterDTO){
        //đặt Rank mặc định cho user
        UserRank userRank = userRankService.getById((long) 4);

        UserProfile userProfile = new UserProfile();
        userProfile.setFullName(userRegisterDTO.getFullName());
        userProfile.setEmail(userRegisterDTO.getEmail());
        userProfile.setAddress(userRegisterDTO.getAddress());
        userProfile.setIdentityNumber(userRegisterDTO.getIdentityNumber());
        userProfile.setPhone(userRegisterDTO.getPhone());
        userProfile.setRank(userRegisterDTO.getRank());
        userProfile.setDayOfBirth(userRegisterDTO.getDayOfBirth());
        userProfileService.save(userProfile);
        System.out.println(userProfile.getDayOfBirth());

        User user = new User();
        user.setUserName(userRegisterDTO.getUserName());
        user.setPassword(userRegisterDTO.getPassword());
        user.setUserProfile(userProfile);


        Set userRole =new HashSet();
        userRole.add(roleService.findByid((long) 3));
        user.setRoles(userRole);
        return ResponseEntity.ok(userService.save(user));
    }
}
