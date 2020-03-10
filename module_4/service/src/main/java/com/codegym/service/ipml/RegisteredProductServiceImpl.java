package com.codegym.service.ipml;

import com.codegym.dao.DTO.RegisteredProductDTO;
import com.codegym.dao.DTO.RegisteredProductDetailDTO;
import com.codegym.dao.entity.Auction;
import com.codegym.dao.entity.RegisteredProduct;
import com.codegym.dao.repository.AuctionRepository;
import com.codegym.dao.repository.RegisteredProductRepository;
import com.codegym.service.RegisteredProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RegisteredProductServiceImpl implements RegisteredProductService {
    @Autowired
    RegisteredProductRepository registeredProductRepository;
    @Autowired
    AuctionRepository auctionRepository;

//    @Override
//    public List<RegisteredProductDTO> getAllRegisteredProductEndDay() {
//        List<RegisteredProduct> registeredProducts = (List<RegisteredProduct>) registeredProductRepository.findAll();
//        List<RegisteredProduct> registeredProducts1 = new ArrayList<RegisteredProduct>();
//        for (RegisteredProduct registeredProduct:registeredProducts){
//            if (registeredProduct.getProduct().getEndDay().compareTo(new Date()) >0){
//                registeredProducts1.add(registeredProduct);
//            }
//        }
//        List<RegisteredProductDTO> registeredProductDTOS =new ArrayList<RegisteredProductDTO>();
//        for (RegisteredProduct registeredProduct:registeredProducts1){
//            RegisteredProductDTO registeredProductDTO = new RegisteredProductDTO();
//            registeredProductDTO.setCatalogue(registeredProduct.getProduct().getProductCatalogue().getName());
//            registeredProductDTO.setId(registeredProduct.getId());
//            registeredProductDTO.setCurrentPrice(registeredProduct.getCurrentPrice());
//            registeredProductDTO.setEndDay(registeredProduct.getProduct().getEndDay());
//            registeredProductDTO.setImg(registeredProduct.getProduct().getImg());
//            registeredProductDTO.setNameProduct(registeredProduct.getProduct().getName());
//            registeredProductDTOS.add(registeredProductDTO);
//        }
//        return registeredProductDTOS;
//    }
    @Override
    public RegisteredProductDetailDTO getByIdRegisterProduct(Long id) {
       RegisteredProduct registeredProduct = registeredProductRepository.findById(id).orElse(null);

       RegisteredProductDetailDTO registeredProductDetailDTO =new RegisteredProductDetailDTO();
        List <Auction> auctions = auctionRepository.findTop5ByRegisteredProductIdOrderByBetTimeDesc(id);
            registeredProductDetailDTO.setId(registeredProduct.getId());
            registeredProductDetailDTO.setProductId(registeredProduct.getProduct().getId());
            registeredProductDetailDTO.setNameProduct(registeredProduct.getProduct().getName());
            registeredProductDetailDTO.setContractAddress(registeredProduct.getProduct().getContractAddress());
            registeredProductDetailDTO.setContractPhoneNumber(registeredProduct.getProduct().getContractPhoneNumber());
            registeredProductDetailDTO.setCurrentPrice(registeredProduct.getCurrentPrice());
            registeredProductDetailDTO.setEndDay(registeredProduct.getProduct().getEndDay());
            registeredProductDetailDTO.setImg(registeredProduct.getProduct().getImg());
            registeredProductDetailDTO.setMinBet(registeredProduct.getProduct().getMinBet());
            registeredProductDetailDTO.setCatalogue(registeredProduct.getProduct().getProductCatalogue().getName());
            registeredProductDetailDTO.setProductInfo(registeredProduct.getProduct().getProductInfo());
            registeredProductDetailDTO.setStartDay(registeredProduct.getProduct().getStartDay());
            registeredProductDetailDTO.setStartPrice(registeredProduct.getProduct().getStartPrice());
        List<Date> arrayBetTime =new ArrayList<>();
        List<String> arrayUser= new ArrayList<> ();
        List<Long> arrayBetPrice= new ArrayList<> ();
        for (Auction auction: auctions){
            arrayBetTime.add(auction.getBetTime());
            arrayUser.add(auction.getUser().getUserName());
            arrayBetPrice.add(auction.getBetPrice());
        }
        registeredProductDetailDTO.setBetPriceList(arrayBetPrice);
        registeredProductDetailDTO.setBetTimeList(arrayBetTime);
        registeredProductDetailDTO.setUserList(arrayUser);
        return registeredProductDetailDTO;
    }
    @Override
    public Page<RegisteredProductDTO> getAllRegisteredProduct(Pageable pageable,String catalogue, Date nowDay) {
        Page<RegisteredProduct> registeredProducts = registeredProductRepository.findAllByProductProductCatalogueNameContainingAndProductEndDayGreaterThan(pageable, catalogue, nowDay);
        Page<RegisteredProductDTO> registeredProductDTOS = registeredProducts.map(registeredProduct -> {
            RegisteredProductDTO registeredProductDTO = new RegisteredProductDTO();
            registeredProductDTO.setCatalogue(registeredProduct.getProduct().getProductCatalogue().getName());
            registeredProductDTO.setId(registeredProduct.getId());
            registeredProductDTO.setCurrentPrice(registeredProduct.getCurrentPrice());
            registeredProductDTO.setEndDay(registeredProduct.getProduct().getEndDay());
            registeredProductDTO.setImg(registeredProduct.getProduct().getImg());
            registeredProductDTO.setNameProduct(registeredProduct.getProduct().getName());
            return registeredProductDTO;
        });
        return registeredProductDTOS;
    }

    @Override
    public Page<RegisteredProductDTO> getAllRegisteredProductByNamePriceCatalogue(Pageable pageable, String name, Long price1,Long price2, String catalogue, Date nowDay) {

        Page<RegisteredProduct> registeredProducts =registeredProductRepository.findAllByProductNameContainingAndCurrentPriceBetweenAndProductProductCatalogueNameContainingAndProductEndDayGreaterThan(pageable,name, price1,price2, catalogue,nowDay);
        Page<RegisteredProductDTO> registeredProductDTOS = registeredProducts.map(registeredProduct -> {
            RegisteredProductDTO registeredProductDTO = new RegisteredProductDTO();
            registeredProductDTO.setCatalogue(registeredProduct.getProduct().getProductCatalogue().getName());
            registeredProductDTO.setId(registeredProduct.getId());
            registeredProductDTO.setCurrentPrice(registeredProduct.getCurrentPrice());
            registeredProductDTO.setEndDay(registeredProduct.getProduct().getEndDay());
            registeredProductDTO.setImg(registeredProduct.getProduct().getImg());
            registeredProductDTO.setNameProduct(registeredProduct.getProduct().getName());
            return registeredProductDTO;
        });
        return registeredProductDTOS;
    }

//    @Override
//    public List<RegisteredProduct> findAllByProductStartPriceBetween(Long number1, Long number2) {
//        return registeredProductRepository.findAllByProductStartPriceBetween(number1,number2);
//    }
//
//    @Override
//    public Page<RegisteredProductDTO> getAllRegisteredProductByCatalogue(Pageable pageable, String catalogue, Date nowDay) {
//        Page<RegisteredProduct> registeredProducts = registeredProductRepository.findAllByProductProductCatalogueNameContainingAndProductEndDayGreaterThan(pageable, catalogue , nowDay);
//        Page<RegisteredProductDTO> registeredProductDTOS = registeredProducts.map(registeredProduct -> {
//            RegisteredProductDTO registeredProductDTO = new RegisteredProductDTO();
//            registeredProductDTO.setCatalogue(registeredProduct.getProduct().getProductCatalogue().getName());
//            registeredProductDTO.setId(registeredProduct.getId());
//            registeredProductDTO.setCurrentPrice(registeredProduct.getCurrentPrice());
//            registeredProductDTO.setEndDay(registeredProduct.getProduct().getEndDay());
//            registeredProductDTO.setImg(registeredProduct.getProduct().getImg());
//            registeredProductDTO.setNameProduct(registeredProduct.getProduct().getName());
//            return registeredProductDTO;
//        });
//        return registeredProductDTOS;
//    }
//
}
