package com.codegym.web_service.controller;

import com.codegym.dao.DTO.JwtResponse;
import com.codegym.dao.DTO.UserDTO;
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
        System.out.println(user.getPassword());
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword())
        );
        System.out.println("co hay khong");
        UserDetails userDetails = userServiceImpl
                .loadUserByUsername(authentication.getName());
        System.out.println("co hay khong");
        String jwtToken=jwtTokenUtil.generateToken(userDetails);
        System.out.println("co hay khong");
        return ResponseEntity.ok( new JwtResponse(jwtToken));
    }
}

