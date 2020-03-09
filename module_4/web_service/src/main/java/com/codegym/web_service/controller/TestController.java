package com.codegym.web_service.controller;

import com.codegym.dao.DTO.JwtResponse;
import com.codegym.dao.DTO.UserDTO;
import com.codegym.dao.entity.Role;
import com.codegym.dao.entity.User;
import com.codegym.service.ipml.UserServiceImpl;
import com.codegym.web_service.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "Authorization")
@RequestMapping("")
public class TestController {
    @Autowired(required = false)
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired(required = false)
    UserServiceImpl userServiceImpl;
    @Autowired
    private UserServiceImpl userService;
    private UserDTO userDTO;
    @GetMapping("/admin")
    public ResponseEntity<?> helloAdmin() {
        userDTO=new UserDTO("admin","Hello");
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/user")
    public ResponseEntity<?> helloUser() {
        userDTO=new UserDTO("user","Hello");
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/member")
    public ResponseEntity<?> helloMember() {
        userDTO=new UserDTO("member","Hello");
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO user){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword())
        );
        UserDetails userDetails = userServiceImpl
                .loadUserByUsername(authentication.getName());
        String jwtToken=jwtTokenUtil.generateToken(userDetails);
        String username =  userDetails.getUsername();
        String rolename = getMaxRoleName(username) ;
        return ResponseEntity.ok( new JwtResponse(jwtToken , username , rolename));
    }
    public String getMaxRoleName(String username){
        String rolename = "ROLE_VISITOR";
        Set<String> role_names =  new HashSet<>();
        User user = userService.findByUserName(username);
        Set<Role> roles = user.getRoles();
        for (Role role: roles) {
            role_names.add(role.getName());
        }
        if (role_names.contains("ROLE_USER")){rolename = "ROLE_USER";}
        if (role_names.contains("ROLE_MEMBER")){rolename = "ROLE_MEMBER";}
        if (role_names.contains("ROLE_ADMIN")){rolename = "ROLE_ADMIN";}
        return rolename;
    }
}

