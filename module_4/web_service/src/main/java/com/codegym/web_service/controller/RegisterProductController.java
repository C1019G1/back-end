package com.codegym.web_service.controller;

import com.codegym.dao.DTO.RegisteredProductDTO;
import com.codegym.dao.DTO.RegisteredProductDetailDTO;
import com.codegym.dao.DTO.UserTransactionDTO;
import com.codegym.dao.entity.UserTransaction;
import com.codegym.service.RegisteredProductService;
import com.codegym.service.AuctionService;
import com.codegym.service.UserTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@EnableScheduling
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("product")
public class RegisterProductController {
    @Autowired
    RegisteredProductService registeredProductService;
    @Autowired
    AuctionService auctionService;
    @Autowired
    UserTransactionService userTransactionService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getByIdRegisterProduct(@PathVariable Long id) {
        RegisteredProductDetailDTO registeredProductDetailDTO = registeredProductService.getByIdRegisterProduct(id);
        return new ResponseEntity<>(registeredProductDetailDTO, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllRegisteredProduct(@RequestParam("page") int page,
                                                     @RequestParam("size") int size,
                                                     @RequestParam("catalogue") String catalogue) {
        Date nowDay = new Date();
        Page<RegisteredProductDTO> registeredProductDTOS = registeredProductService.getAllRegisteredProduct(PageRequest.of(page, size), catalogue, nowDay);
        return new ResponseEntity<>(registeredProductDTOS.getContent(), HttpStatus.OK);
    }

    @GetMapping(value = "/search", params = {"page", "size", "name", "price1", "price2", "catalogue"})
    public ResponseEntity<?> getAllRegisteredProductByNamePriceCatalogue(@RequestParam("page") int page,
                                                                         @RequestParam("size") int size,
                                                                         @RequestParam("name") String name,
                                                                         @RequestParam("price1") Long price1,
                                                                         @RequestParam("price2") Long price2,
                                                                         @RequestParam("catalogue") String catalogue) {
        Date nowDay = new Date();
        Page<RegisteredProductDTO> registeredProductDTOS = registeredProductService.getAllRegisteredProductByNamePriceCatalogue(PageRequest.of(page, size), name, price1, price2, catalogue, nowDay);
        return new ResponseEntity<>(registeredProductDTOS.getContent(), HttpStatus.OK);
    }

    //    @Scheduled(fixedRate = 15000)
//    @GetMapping(value = "schedule")
//    public ResponseEntity<?> schedue() {
//        System.out.println("Xin chào bạn");
//        return new ResponseEntity<>("thành công",HttpStatus.OK);
//    }
    @GetMapping("/autoLoading")
    public ResponseEntity<?> find() {
        userTransactionService.autoLoadingUserTransaction();
        return new ResponseEntity<>("Bạn đã lưu thành công", HttpStatus.OK);
    }

    @GetMapping(value = "/transaction", params = {"page", "size"})
    public ResponseEntity<?> getAllTransaction(@RequestParam("page") int page,
                                               @RequestParam("size") int size) {
        Page<UserTransactionDTO> userTransactionDTOS = userTransactionService.getAllTransaction(PageRequest.of(page, size));
        return new ResponseEntity<>(userTransactionDTOS, HttpStatus.OK);
    }
    @GetMapping(value = "/transaction-search", params = {"page", "size","buyer","seller","productName","firstDateSt","lastDateSt","status"})
    public ResponseEntity<?> getAllTransaction(@RequestParam("page") int page,
                                               @RequestParam("size") int size,
                                               @RequestParam("buyer") String buyer,
                                               @RequestParam("seller") String seller,
                                               @RequestParam("productName") String productName,
                                               @RequestParam("firstDateSt") String firstDateSt,
                                               @RequestParam("lastDateSt") String lastDateSt,
                                               @RequestParam("status") Boolean status) throws ParseException {
        System.out.println("---------------------------page="+page+ "size:"+size+"fee="+buyer);

        DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS", Locale.US);
        DateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS", Locale.US);
        Date firstDate = dateFormat1.parse(firstDateSt);
        Date lastDate = dateFormat2.parse(lastDateSt);
        System.out.println(firstDate + "---------------------"+lastDate);
        Page<UserTransactionDTO> userTransactionDTOS = userTransactionService.searchTransaction(PageRequest.of(page, size),buyer,seller,productName,firstDate,lastDate,status);
        return new ResponseEntity<>(userTransactionDTOS.getContent(), HttpStatus.OK);
    }
}
