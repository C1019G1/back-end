package com.codegym.web_service.controller;

import com.codegym.dao.DTO.AdminUserProfileDTO;
import com.codegym.dao.DTO.UserRegisterDTO;
import com.codegym.dao.entity.UserRank;
import com.codegym.service.UserProfileService;
import com.codegym.service.UserRankService;
import com.codegym.service.ipml.UserServiceImpl;
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
    UserServiceImpl userService;
    @Autowired
    UserRankService userRankService;
    @Autowired
    UserProfileService userProfileService;
    @GetMapping("user-list")
    public ResponseEntity<Page<AdminUserProfileDTO>> getUserList(@RequestParam(name = "page") int page,
                                                                 @RequestParam(name = "size") int size,
                                                                 @RequestParam(name = "name", defaultValue = "") String name,
                                                                 @RequestParam(name = "rank", defaultValue = "") String rank
    ) {
        Page<AdminUserProfileDTO> adminUserProfileDTOS;
        adminUserProfileDTOS = userService.getUsersProfileByNameByRank(PageRequest.of(page, size), name, rank);
        return new ResponseEntity(adminUserProfileDTOS, HttpStatus.OK);
    }

    @GetMapping("find")
    public ResponseEntity<AdminUserProfileDTO> searchUser(@RequestParam(name = "id", defaultValue = "") Long id,
                                                          @RequestParam(name = "email", defaultValue = "") String email) {
        AdminUserProfileDTO userProfileDTO = new AdminUserProfileDTO();
        if (id != null && !email.equals("")) {
            userProfileDTO = userService.getUserProfileDTOByIdAndEmail(id, email);
        } else {
            if (id != null) {
                userProfileDTO = userService.getUserProfileDTOById(id);
            } else {
                if (!email.equals("")) {
                    userProfileDTO = userService.getUserProfileDTOByEmail(email);
                }
            }
        }
        if (userProfileDTO != null) {
            return new ResponseEntity(userProfileDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("rank-list")
    public ResponseEntity<Iterable<UserRank>> getRankList() {
        return new ResponseEntity(userRankService.getAllRank(), HttpStatus.OK);
    }
    @PostMapping("user-register")
    public ResponseEntity userRegisterByAdmin(@RequestBody UserRegisterDTO userRegisterDTO) {
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
