package com.codegym.web_service.controller;

import com.codegym.dao.DTO.RegisteredProductDTO;
import com.codegym.dao.DTO.RegisteredProductDetailDTO;
import com.codegym.dao.entity.RegisteredProduct;
import com.codegym.service.RegisteredProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping(value = "/search", params = {"name"})
    public ResponseEntity<?> getAllRegisteredProductByName(@RequestParam ("name") String name) {
        List<RegisteredProduct> registeredProducts = registeredProductService.getAllRegisteredProductByNameContaining(name);
        return new ResponseEntity<>(registeredProducts, HttpStatus.OK);
    }
    @GetMapping(value = "/search", params = {"name","price","catalogue"})
    public ResponseEntity<?> getAllRegisteredProductByName(@RequestParam ("name") String name,
                                                            @RequestParam ("price") Long price,
                                                           @RequestParam ("catalogue") String catalogue) {
        List<RegisteredProduct> registeredProducts = registeredProductService.getAllRegisteredProductByNamePriceCatalogue(name,price,catalogue);
        return new ResponseEntity<>(registeredProducts, HttpStatus.OK);
    }
}
