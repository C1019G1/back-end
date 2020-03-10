package com.codegym.web_service.controller;

import com.codegym.dao.DTO.HistoryRegisterProductDTO;
import com.codegym.dao.entity.Product;
import com.codegym.dao.entity.User;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "Authorization")
@RequestMapping("product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("prod/{userId}")
    public ResponseEntity<?> getRegisterProductForUser(
            @PathVariable("userId") Long userId,
            @RequestParam(name = "pageable") int pageable,
            @RequestParam(name = "size") int size
            ) {
       List<Product> listProduct = productService.findProductByUserId(userId,PageRequest.of(pageable, size));
        List<HistoryRegisterProductDTO> listProductDto = new ArrayList<>();
        for (Product item : listProduct) {
            HistoryRegisterProductDTO ProductDto = new HistoryRegisterProductDTO();
            ProductDto.setId(item.getId());
            ProductDto.setUser_id(item.getUser().getId());
            ProductDto.setName_product(item.getName());
            ProductDto.setEnd_day(item.getEndDay());
            ProductDto.setStart_day(item.getStartDay());
            ProductDto.setStart_price(item.getStartPrice());
            ProductDto.setProduct_info(item.getProductInfo());
            ProductDto.setStatus(item.isStatus());
            listProductDto.add(ProductDto);
        }
        return new ResponseEntity<>(listProductDto, HttpStatus.OK);
    }
}
