package com.codegym.web_service.controller;

import com.codegym.common.RandomString;
import com.codegym.common.SendGmailService;
import com.codegym.dao.DTO.*;
import com.codegym.dao.entity.*;
import com.codegym.service.UserLockListService;
import com.codegym.service.UserLoginHistoryService;
import com.codegym.service.UserProfileService;
import com.codegym.service.ipml.UserServiceImpl;
import com.codegym.web_service.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.IconUIResource;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "Authorization")
@RequestMapping("")
public class UserController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    UserProfileService userProfileService;
    @Autowired(required = false)
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    UserLoginHistoryService userLoginHistoryService;
    @Autowired
    UserLockListService userLockListService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserRegisterDTO userRegisterDTO) {
        // kiểm tra username hoặc email đã tồn tại trong database?
        if (userService.checkUsernameIsExisted(userRegisterDTO.getUserName())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Tài khoản đã tồn tại");
        }
        if (userProfileService.checkEmailIsExisted(userRegisterDTO.getEmail())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("email này đã được đăng ký");
        }
        return ResponseEntity.ok(userService.save(userRegisterDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        UserDetails userDetails = null;
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword())
            );
            userDetails = userService
                    .loadUserByUsername(authentication.getName());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Thông tin đăng nhập không chính xác");
        }
        String jwtToken = jwtTokenUtil.generateToken(userDetails);
        String username = userDetails.getUsername();
        String rolename = getMaxRoleName(username);

        User user = userService.findByUserName(username);
        UserLockList userlock = userLockListService.checkStatus(user.getId());
        if (userlock != null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("tài khoản của bạn đang bị khóa cho đến :  " + userlock.getDayLockEnd()
                            + " .Vì lí do : " + userlock.getReasonLock()
                    );
        }
        userLoginHistoryService.saveNewLoginSession(user);
        return ResponseEntity.ok(new JwtResponse(jwtToken, username, rolename));
    }

    public String getMaxRoleName(String username) {
        String rolename = "ROLE_VISITOR";
        Set<String> role_names = new HashSet<>();
        User user = userService.findByUserName(username);
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            role_names.add(role.getName());
        }
        if (role_names.contains("ROLE_USER")) {
            rolename = "ROLE_USER";
        }
        if (role_names.contains("ROLE_MEMBER")) {
            rolename = "ROLE_MEMBER";
        }
        if (role_names.contains("ROLE_ADMIN")) {
            rolename = "ROLE_ADMIN";
        }
        return rolename;
    }

    @PostMapping("/login-history")
    public ResponseEntity<?> getAllHistoryLogin(@RequestBody String username) {
        User user = userService.findByUserName("admin");
        List<String> userLoginHistoryList = userLoginHistoryService.getAllLoginTime(user);
        return ResponseEntity.ok(userLoginHistoryList);
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody() ChangePasswordDTO changePasswordDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(changePasswordDTO.getUsername(), changePasswordDTO.getOldPassword())
            );
            userService.changePassword(changePasswordDTO.getUsername(), changePasswordDTO.getNewPassword());
            return ResponseEntity.ok("");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Thông tin tài khoản không chính xác");
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody() ResetPasswordDTO resetPasswordDTO) {

        try {
            User user = userService.findByUserName(resetPasswordDTO.getUserName());
            UserProfile userProfile = userProfileService.getUserProfileByEmail(resetPasswordDTO.getEmail());
            if (user.getUserProfile().equals(userProfile)) {
                RandomString randomString = new RandomString();
                String newPassword = randomString.getAlphaNumericString(20);
                System.out.println(newPassword);
                user.setPassword(newPassword);
                userService.changePassword(user.getUserName(),newPassword);
                SendGmailService sendGmailService = new SendGmailService();
                sendGmailService.setReceiverMail(resetPasswordDTO.getEmail());
                sendGmailService.setTitle("Mật khẩu mới");
                sendGmailService.setContent("mật khẩu mới của bạn là " + newPassword);
                sendGmailService.sendMail();
                return ResponseEntity.ok("");
            }
            else {
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body("Thông tin tài khoản không chính xác");
            }
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Thông tin tài khoản không chính xác");
        }
    }
}
