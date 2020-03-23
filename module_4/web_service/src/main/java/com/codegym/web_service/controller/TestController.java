package com.codegym.web_service.controller;

import com.codegym.dao.DTO.AdminProductDetailDTO;
import com.codegym.dao.DTO.AdminProductManagerDTO;
import com.codegym.dao.DTO.JwtResponse;
import com.codegym.dao.DTO.UserDTO;
import com.codegym.dao.entity.ProductCatalogue;
import com.codegym.dao.entity.Role;
import com.codegym.dao.entity.User;
import com.codegym.service.AdminProductManagerService;
import com.codegym.service.ipml.UserServiceImpl;
import com.codegym.web_service.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

    @Autowired
    AdminProductManagerService adminProductManagerService;

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
    @GetMapping("/prod-manager")
    public ResponseEntity<?> getAllProductManager(@RequestParam("page") int page,
                                                  @RequestParam("size") int size) {
        Page<AdminProductManagerDTO> adminProductManagerDTOS = adminProductManagerService.getAllProduct(PageRequest.of(page, size));
        return new ResponseEntity<>(adminProductManagerDTOS.getContent(), HttpStatus.OK);
    }

    @GetMapping (value = "/search", params = {"page","size","name","catalogue","userName","startPrice","status"})
    public ResponseEntity<?> getAllProductByNameProductAndCatalogueAndUserNameAndStartPriceAndStatus(@RequestParam("page") int page,
                                                                                                     @RequestParam("size") int size,
                                                                                                     @RequestParam ("nameProduct") String nameProduct,
                                                                                                     @RequestParam ("catalogue") ProductCatalogue catalogue,
                                                                                                     @RequestParam ("userName") String userName,
                                                                                                     @RequestParam ("startPrice") Long startPrice,
                                                                                                     @RequestParam ("status") Boolean status) {
        Page<AdminProductManagerDTO> adminProductManagerDTOS = adminProductManagerService.getAllProductByNameProductAndCatalogueAndUserNameAndStartPriceAndStatus(nameProduct,catalogue,userName,startPrice,status,PageRequest.of(page, size));
        return new ResponseEntity<>(adminProductManagerDTOS.getContent(), HttpStatus.OK);
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<?> getByIdRegisterProduct(@PathVariable Long id) {
//        AdminProductDetailDTO adminProductDetailDTO = AdminProductManagerService.getByIdProduct(id);
//        return new ResponseEntity<>(adminProductDetailDTO, HttpStatus.OK);
//    }

}

