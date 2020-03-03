package com.codegym.web_service.controller;

import com.codegym.dao.entity.Role;
import com.codegym.dao.entity.User;
import com.codegym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("admin")
public class AdminController {
    @Autowired
    UserService userService;

    @GetMapping("user-list")
    public ResponseEntity<?> helloAdmin() {
        List<User> user = userService.getAllUser();
        System.out.println(user);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
