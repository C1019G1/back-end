package com.codegym.web_service.controller;

import com.codegym.dao.DTO.AdminUserProfileDTO;
import com.codegym.dao.entity.User;
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
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("admin")
public class AdminController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    UserRankService userRankService;

    @GetMapping("user-list")
    public ResponseEntity<?> getUserList(@RequestParam(name = "page") int page,
                                         @RequestParam(name = "size") int size,
                                         @RequestParam(name = "name", defaultValue = "") String name,
                                         @RequestParam(name = "rank", defaultValue = "") String rank
    ) {
        Page<AdminUserProfileDTO> adminUserProfileDTOS;
        adminUserProfileDTOS = userService.getUsersProfileByNameByRank(PageRequest.of(page, size), name, rank);
        return new ResponseEntity<>(adminUserProfileDTOS, HttpStatus.OK);
    }

    @GetMapping("find")
    public ResponseEntity<?> searchUser(@RequestParam(name = "id", defaultValue = "") Long id,
                                        @RequestParam(name = "email", defaultValue = "") String email) {
        AdminUserProfileDTO userProfileDTO = new AdminUserProfileDTO();
        if (id != null && email != "") {
            userProfileDTO = userService.getUserProfileDTOByIdAndEmail(id, email);
        } else {
            if (id != null) {
                userProfileDTO = userService.getUserProfileDTOById(id);
            } else {
                if (email != "") {
                    userProfileDTO = userService.getUserProfileDTOByEmail(email);
                }
            }
        }
        if (userProfileDTO != null) {
            return new ResponseEntity<AdminUserProfileDTO>(userProfileDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<AdminUserProfileDTO>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("rank-list")
    public ResponseEntity<?> getRankList() {
        return new ResponseEntity<>(userRankService.getAllRank(), HttpStatus.OK);
    }
}
