package com.codegym.web_service.controller;

import com.codegym.common.SendGmailService;
import com.codegym.dao.DTO.RegisteredProductDTO;
import com.codegym.dao.DTO.RegisteredProductDetailDTO;
import com.codegym.service.RegisteredProductService;
import com.codegym.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Date;
@EnableScheduling
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("product")
public class RegisterProductController {
    @Autowired
    RegisteredProductService registeredProductService;
    @Autowired
    AuctionService auctionService;
    @GetMapping("/{id}")
    public ResponseEntity<?> getByIdRegisterProduct(@PathVariable Long id) {
        RegisteredProductDetailDTO registeredProductDetailDTO = registeredProductService.getByIdRegisterProduct(id);
        return new ResponseEntity<>(registeredProductDetailDTO, HttpStatus.OK);
    }
    @GetMapping("/list")
    public ResponseEntity<?> getAllRegisteredProduct(@RequestParam("page") int page,
                                                      @RequestParam("size") int size,
                                                      @RequestParam("catalogue") String catalogue) {
        Date nowDay =new Date();
        Page<RegisteredProductDTO> registeredProductDTOS = registeredProductService.getAllRegisteredProduct(PageRequest.of(page, size),catalogue ,nowDay);
        return new ResponseEntity<>(registeredProductDTOS.getContent(), HttpStatus.OK);
    }
    @GetMapping(value = "/search", params = {"page","size","name","price1","price2","catalogue"})
    public ResponseEntity<?> getAllRegisteredProductByNamePriceCatalogue(@RequestParam("page") int page,
                                                           @RequestParam("size") int size,
                                                           @RequestParam ("name") String name,
                                                           @RequestParam ("price1") Long price1,
                                                           @RequestParam ("price2") Long price2,
                                                           @RequestParam ("catalogue") String catalogue) {
        Date nowDay =new Date();
        Page<RegisteredProductDTO> registeredProductDTOS = registeredProductService.getAllRegisteredProductByNamePriceCatalogue(PageRequest.of(page, size),name,price1,price2,catalogue,nowDay);
        return new ResponseEntity<>(registeredProductDTOS.getContent(), HttpStatus.OK);
    }
//    @Scheduled(fixedRate = 15000)
//    @GetMapping(value = "schedule")
//    public ResponseEntity<?> schedue() {
//        System.out.println("Xin chào bạn");
//        return new ResponseEntity<>("thành công",HttpStatus.OK);
//    }
}
