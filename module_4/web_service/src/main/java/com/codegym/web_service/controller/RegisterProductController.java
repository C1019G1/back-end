package com.codegym.web_service.controller;

import com.codegym.dao.DTO.RegisteredProductDTO;
import com.codegym.dao.DTO.RegisteredProductDetailDTO;
import com.codegym.dao.entity.RegisteredProduct;
import com.codegym.service.RegisteredProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
    @GetMapping("/page")
    public ResponseEntity<?> getAllRegisteredProduct1(@RequestParam("page") int page,
                                                      @RequestParam("size") int size) {
        Date nowDay =new Date();
        Page<RegisteredProductDTO> registeredProductDTOS = registeredProductService.getAllRegisteredProduct(PageRequest.of(page, size) ,nowDay);
        return new ResponseEntity<>(registeredProductDTOS, HttpStatus.OK);
    }
    @GetMapping(value = "/search", params = {"name","price1","price2","catalogue"})
    public ResponseEntity<?> getAllRegisteredProductByName(Pageable pageable,@RequestParam ("name") String name,
                                                           @RequestParam ("price1") Long price1,
                                                           @RequestParam ("price2") Long price2,
                                                           @RequestParam ("catalogue") String catalogue) {
        Date nowDay =new Date();
        Page<RegisteredProductDTO> registeredProductDTOS = registeredProductService.getAllRegisteredProductByNamePriceCatalogue(pageable,name,price1,price2,catalogue,nowDay);
        return new ResponseEntity<>(registeredProductDTOS, HttpStatus.OK);
    }
    @GetMapping(value = "/search", params = {"price1","price2"})
    public ResponseEntity<?> getAllRegisteredProductByName(@RequestParam ("price1") Long price1,
                                                           @RequestParam ("price2") Long price2)
                                                            {
        List<RegisteredProduct> registeredProducts = registeredProductService.findAllByProductStartPriceBetween(price1,price2);
        return new ResponseEntity<>(registeredProducts, HttpStatus.OK);
    }
}
