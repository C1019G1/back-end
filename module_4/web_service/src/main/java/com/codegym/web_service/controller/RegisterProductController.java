package com.codegym.web_service.controller;

import com.codegym.dao.DTO.RegisteredProductDTO;
import com.codegym.dao.DTO.RegisteredProductDetailDTO;
import com.codegym.dao.entity.Auction;
import com.codegym.service.RegisteredProductService;
import com.codegym.service.ipml.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("product")
public class RegisterProductController {
    @Autowired
    RegisteredProductService registeredProductService;
    @Autowired
    AuctionService auctionService;

//    @GetMapping("/list")
//    public ResponseEntity<?> getAllRegisteredProduct() {
//        List<RegisteredProductDTO> registeredProductDTOS = registeredProductService.getAllRegisteredProductEndDay();
//        return new ResponseEntity<>(registeredProductDTOS, HttpStatus.OK);
//    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getByIdRegisterProduct(@PathVariable Long id) {
        RegisteredProductDetailDTO registeredProductDetailDTO = registeredProductService.getByIdRegisterProduct(id);
        System.out.println(registeredProductDetailDTO);
        return new ResponseEntity<>(registeredProductDetailDTO, HttpStatus.OK);
    }
    @GetMapping("list")
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
//    @GetMapping(value = "/search", params = {"catalogue"})
//    public ResponseEntity<?> getAllRegisteredProductByCatalogue(@RequestParam ("catalogue") String catalogue,
//                                                           @RequestParam("page") int page,
//                                                           @RequestParam("size") int size)
//                                                            {
//        Date nowDay =new Date();
//        Page<RegisteredProductDTO> registeredProductDTOS = registeredProductService.getAllRegisteredProductByCatalogue(PageRequest.of(page, size) ,catalogue, nowDay);
//        return new ResponseEntity<>(registeredProductDTOS.getContent(), HttpStatus.OK);
//    }
    @GetMapping(value = "/search", params = {"id"})
    public ResponseEntity<?> getAuctionById(@RequestParam ("id") Long id) {
     List <Auction>  auctions = auctionService.findAuctionById(id);
        List<Date> arrayBetTime =new ArrayList<>();
        List<String> arrayUser= new ArrayList<> ();
        List<Long> arrayBetPrice= new ArrayList<> ();
        for (Auction auction: auctions) {
            System.out.println(auction.getBetTime());
            arrayBetTime.add(auction.getBetTime());
            arrayUser.add(auction.getUser().getUserName());
            arrayBetPrice.add(auction.getBetPrice());
        }
        System.out.println(arrayBetPrice);
        System.out.println(arrayBetTime);
        System.out.println(arrayUser);
        return new ResponseEntity<>(arrayBetTime, HttpStatus.OK);
    }
}
