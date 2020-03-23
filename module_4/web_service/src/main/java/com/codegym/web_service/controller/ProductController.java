package com.codegym.web_service.controller;

import com.codegym.dao.DTO.HistoryAuctionProductDTO;
import com.codegym.dao.DTO.HistoryRegisterProductDTO;
import com.codegym.dao.entity.Product;
import com.codegym.service.HistoryAuctionProductService;
import com.codegym.service.HistoryRegisterProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "Authorization")
@RequestMapping("product")
public class ProductController {

    @Autowired
    private HistoryRegisterProductsService historyRegisterProductsService;

    @Autowired
    private HistoryAuctionProductService historyAuctionProductService;

    //History Register Product
    @GetMapping("/reg")
    public ResponseEntity<?> getRegisterProductForUser(
            @RequestParam("userId") Long userId,
            @RequestParam("page") int page,
            @RequestParam("size") int size
            ) {
       Page<Product> listProduct = historyRegisterProductsService.findProductByUserId(PageRequest.of(page, size),userId);
       Page<HistoryRegisterProductDTO> listProductDto = listProduct.map(product -> {
            HistoryRegisterProductDTO ProductDto = new HistoryRegisterProductDTO();
            ProductDto.setId(product.getId());
            ProductDto.setUser_id(product.getUser().getId());
            ProductDto.setName_product(product.getName());
            ProductDto.setEnd_day(product.getEndDay());
            ProductDto.setStart_day(product.getStartDay());
            ProductDto.setStart_price(product.getStartPrice());
            ProductDto.setProduct_info(product.getProductInfo());
            ProductDto.setStatus(product.isPendingStatus());
            return ProductDto;
        });
        System.out.println(listProductDto.getContent());
        return new ResponseEntity<>(listProductDto.getContent(), HttpStatus.OK);
    }

// History Auction Product
    @GetMapping("/auc")
    public ResponseEntity<?> getHistoryAuctionProduct(
           @RequestParam("userId") Long userId,
            @RequestParam(name = "pageable") int pageable,
            @RequestParam(name = "size") int size
    ) {
        Page<Product> listProduct1 = historyAuctionProductService.findAuctionProductByUserId(userId,PageRequest.of(pageable, size));
        Page<HistoryAuctionProductDTO> listProductDto =listProduct1.map(product ->{
            HistoryAuctionProductDTO AuctionProduct = new HistoryAuctionProductDTO();
            AuctionProduct.setId(product.getId());
            AuctionProduct.setUser_id(product.getUser().getId());
            AuctionProduct.setName_product(product.getName());
            AuctionProduct.setEnd_day(product.getEndDay());
            AuctionProduct.setStart_day(product.getStartDay());
            AuctionProduct.setStart_price(product.getStartPrice());
            AuctionProduct.setProduct_info(product.getProductInfo());
            AuctionProduct.setStatus(product.isPendingStatus());
            return AuctionProduct;
        });
        return new ResponseEntity<>(listProductDto.getContent(), HttpStatus.OK);
    }
}
