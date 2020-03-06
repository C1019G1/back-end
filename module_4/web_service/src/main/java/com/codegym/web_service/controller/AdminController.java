package com.codegym.web_service.controller;

import com.codegym.dao.DTO.AdminUserProfileDTO;
import com.codegym.dao.entity.UserRank;
import com.codegym.service.UserRankService;
import com.codegym.service.ipml.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("admin")
public class AdminController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    UserRankService userRankService;

    @GetMapping("user-list")
    public ResponseEntity<?> getUserList(@RequestParam("page") int page,
                                         @RequestParam("size") int size,
                                         @RequestParam("search") String search) {
        Page<AdminUserProfileDTO> adminUserProfileDTOS;
        adminUserProfileDTOS= userService.getUserProfileAdmin(PageRequest.of(page, size));
        return new ResponseEntity<>(adminUserProfileDTOS, HttpStatus.OK);
    }
    @GetMapping("rank-list")
    public ResponseEntity<?> getRankList(){
        List rankList = new ArrayList();
        Iterable<UserRank> userRankList =  userRankService.getAllRank();
        for (UserRank userRank:userRankList) {
            rankList.add(userRank.getName());
        }
        return new ResponseEntity<>(rankList, HttpStatus.OK);
    }
}
