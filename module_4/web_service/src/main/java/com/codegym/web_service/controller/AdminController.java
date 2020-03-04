package com.codegym.web_service.controller;

import com.codegym.dao.DTO.AdminUserProfileDTO;
import com.codegym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("admin")
public class AdminController {
    @Autowired
    UserService userService;

    @GetMapping("user-list")
    public ResponseEntity<?> getUserList(@RequestParam("page") int page,
                                         @RequestParam("size") int size,
                                         @RequestParam("search") String search) {
        Page<AdminUserProfileDTO> adminUserProfileDTOS;
        adminUserProfileDTOS= userService.getUserProfileAdmin(PageRequest.of(page, size));
        return new ResponseEntity<>(adminUserProfileDTOS, HttpStatus.OK);
    }
}
