package com.codegym.web_service.controller;

import com.codegym.dao.DTO.RegisteredProductDTO;
import com.codegym.dao.DTO.RegisteredProductDetailDTO;
import com.codegym.dao.entity.RegisteredProduct;
import com.codegym.service.RegisteredProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("product")
public class RegisterProductController {
    @Autowired
    RegisteredProductService registeredProductService;

    @GetMapping("/list")
    public ResponseEntity<?> getAllRegisteredProduct() {
        List<RegisteredProductDTO> registeredProductDTOS = registeredProductService.getAllRegisteredProductEndDay();
        return new ResponseEntity<>(registeredProductDTOS, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getByIdRegisterProduct(@PathVariable Long id) {
        RegisteredProductDetailDTO registeredProductDetailDTO = registeredProductService.getByIdRegisterProduct(id);
        System.out.println(registeredProductDetailDTO);
        return new ResponseEntity<>(registeredProductDetailDTO, HttpStatus.OK);
    }
//    @GetMapping("/{name}")
//    public ResponseEntity<?> getAllRegisteredProductByName(@PathVariable String name) {
//        List<RegisteredProduct> registeredProducts = registeredProductService.getAllRegisteredProductByName(name);
//        System.out.println(registeredProducts);
//        return new ResponseEntity<>(registeredProducts, HttpStatus.OK);
//    }
}
