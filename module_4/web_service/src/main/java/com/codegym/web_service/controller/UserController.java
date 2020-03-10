package com.codegym.web_service.controller;

import com.codegym.dao.DTO.UserDTO;
import com.codegym.dao.DTO.UserRegisterDTO;
import com.codegym.dao.entity.Role;
import com.codegym.dao.entity.User;
import com.codegym.dao.entity.UserProfile;
import com.codegym.dao.entity.UserRank;
import com.codegym.service.RoleService;
import com.codegym.service.UserProfileService;
import com.codegym.service.UserRankService;
import com.codegym.service.ipml.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    UserServiceImpl userService;
    @Autowired
    UserProfileService userProfileService;
    @Autowired
    private PasswordEncoder bcryptEncoder;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public  ResponseEntity<?> saveUser(@RequestBody UserRegisterDTO userRegisterDTO){
        // kiểm tra username hoặc email đã tồn tại trong database?
        if(userService.checkUsernameIsExisted(userRegisterDTO.getUserName())){
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Tài khoản đã tồn tại");
        }
        if(userProfileService.checkEmailIsExisted(userRegisterDTO.getEmail())){
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("email này đã được đăng ký");
        }

        return ResponseEntity.ok(userService.save(userRegisterDTO));
    }
}
