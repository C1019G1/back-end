package com.codegym.web_service.controller;

import com.codegym.dao.entity.RegisteredProduct;
import com.codegym.service.RegisteredProductService;
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
@RequestMapping("")
public class HomeController {
    @Autowired
    RegisteredProductService registeredProductService;

    @GetMapping("product-list")
    public ResponseEntity<?> getDashBroad() {
        List<RegisteredProduct> registeredProducts = registeredProductService.getAllRegisteredProduct();
        return new ResponseEntity<>(registeredProducts, HttpStatus.OK);
    }
}
