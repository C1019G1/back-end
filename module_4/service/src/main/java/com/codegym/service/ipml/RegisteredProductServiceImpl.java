package com.codegym.service.ipml;

import com.codegym.dao.DTO.RegisteredProductDTO;
import com.codegym.dao.DTO.RegisteredProductDetailDTO;
import com.codegym.dao.entity.Auction;
import com.codegym.dao.entity.Image;
import com.codegym.dao.entity.RegisteredProduct;
import com.codegym.dao.repository.AuctionRepository;
import com.codegym.dao.repository.RegisteredProductRepository;
import com.codegym.service.RegisteredProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class RegisteredProductServiceImpl implements RegisteredProductService {
    @Autowired
    RegisteredProductRepository registeredProductRepository;
    @Autowired
    AuctionRepository auctionRepository;

    @Override
    public RegisteredProductDetailDTO getByIdRegisterProduct(Long id) {
        RegisteredProduct registeredProduct = registeredProductRepository.findById(id).orElse(null);
        RegisteredProductDetailDTO registeredProductDetailDTO = new RegisteredProductDetailDTO();
        assert registeredProduct != null;
        registeredProductDetailDTO.setId(registeredProduct.getId());
        registeredProductDetailDTO.setProductId(registeredProduct.getProduct().getId());
        registeredProductDetailDTO.setNameProduct(registeredProduct.getProduct().getName());
        registeredProductDetailDTO.setContractAddress(registeredProduct.getProduct().getContractAddress());
        registeredProductDetailDTO.setContractPhoneNumber(registeredProduct.getProduct().getContractPhoneNumber());
        registeredProductDetailDTO.setEndDay(registeredProduct.getProduct().getEndDay());
        registeredProductDetailDTO.setImg(this.getImageUrl(registeredProduct.getProduct().getImages()));
        registeredProductDetailDTO.setMinBet(registeredProduct.getProduct().getMinBet());
        registeredProductDetailDTO.setCatalogue(registeredProduct.getProduct().getProductCatalogue().getName());
        registeredProductDetailDTO.setProductInfo(registeredProduct.getProduct().getProductInfo());
        registeredProductDetailDTO.setStartDay(registeredProduct.getProduct().getStartDay());
        registeredProductDetailDTO.setStartPrice(registeredProduct.getProduct().getStartPrice());
        Auction auction1 = auctionRepository.findFirstByRegisteredProductIdOrderByBetPriceDesc(id);
        if (auction1 == null) {
            registeredProductDetailDTO.setCurrentPrice(registeredProduct.getProduct().getStartPrice());
        } else {
            registeredProductDetailDTO.setCurrentPrice(auction1.getBetPrice());
        }
        List<Auction> auctions = auctionRepository.findTop5ByRegisteredProductIdOrderByBetTimeDesc(id);
        List<Date> arrayBetTime = new ArrayList<>();
        List<String> arrayUser = new ArrayList<>();
        List<Long> arrayBetPrice = new ArrayList<>();
        for (Auction auction : auctions) {
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
    public Page<RegisteredProductDTO> getAllRegisteredProduct(Pageable pageable, String catalogue, Date nowDay) {
        Page<RegisteredProduct> registeredProducts = registeredProductRepository.findAllByProductProductCatalogueNameContainingAndProductEndDayGreaterThan(pageable, catalogue, nowDay);
        return getRegisteredProductDTOS(registeredProducts);
    }

    @Override
    public Page<RegisteredProductDTO> getAllRegisteredProductByNamePriceCatalogue(Pageable pageable, String name, Long price1, Long price2, String catalogue, Date nowDay) {

        Page<RegisteredProduct> registeredProducts = registeredProductRepository.findAllByProductNameContainingAndCurrentPriceBetweenAndProductProductCatalogueNameContainingAndProductEndDayGreaterThan(pageable, name, price1, price2, catalogue, nowDay);
        return getRegisteredProductDTOS(registeredProducts);
    }

    private Page<RegisteredProductDTO> getRegisteredProductDTOS(Page<RegisteredProduct> registeredProducts) {
        return registeredProducts.map(registeredProduct -> {
            RegisteredProductDTO registeredProductDTO = new RegisteredProductDTO();
            registeredProductDTO.setCatalogue(registeredProduct.getProduct().getProductCatalogue().getName());
            registeredProductDTO.setId(registeredProduct.getId());
            registeredProductDTO.setEndDay(registeredProduct.getProduct().getEndDay());
            registeredProductDTO.setImg(this.getImageUrl(registeredProduct.getProduct().getImages()));
            registeredProductDTO.setNameProduct(registeredProduct.getProduct().getName());
            Long id = registeredProduct.getId();
            Auction auction1 = auctionRepository.findFirstByRegisteredProductIdOrderByBetPriceDesc(id);
            if (auction1 == null) {
                registeredProductDTO.setCurrentPrice(registeredProduct.getProduct().getStartPrice());
            } else {
                registeredProductDTO.setCurrentPrice(auction1.getBetPrice());
            }
            return registeredProductDTO;
        });
    }

    private List<String> getImageUrl(Set<Image> images){
        List<String> imageUrl= new ArrayList<>();
        images.forEach(image -> imageUrl.add(image.getUrl()));
        return imageUrl;
    }
    @Override
    public List<RegisteredProduct> getAllRegisterProductByEnday(Date nowDay) {
        return registeredProductRepository.findAllByProductEndDayLessThan(nowDay);
    }
}
