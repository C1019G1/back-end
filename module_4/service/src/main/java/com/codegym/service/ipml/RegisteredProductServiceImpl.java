package com.codegym.service.ipml;

import com.codegym.dao.DTO.RegisteredProductDTO;
import com.codegym.dao.DTO.RegisteredProductDetailDTO;
import com.codegym.dao.entity.RegisteredProduct;
import com.codegym.dao.repository.RegisteredProductRepository;
import com.codegym.service.RegisteredProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RegisteredProductServiceImpl implements RegisteredProductService {
    @Autowired
    RegisteredProductRepository registeredProductRepository;

    @Override
    public List<RegisteredProductDTO> getAllRegisteredProductEndDay() {
        List<RegisteredProduct> registeredProducts = (List<RegisteredProduct>) registeredProductRepository.findAll();
        List<RegisteredProduct> registeredProducts1 = new ArrayList<RegisteredProduct>();
        for (RegisteredProduct registeredProduct:registeredProducts){
            if (registeredProduct.getProduct().getEndDay().compareTo(new Date()) >0){
                registeredProducts1.add(registeredProduct);
            }
        }
        List<RegisteredProductDTO> registeredProductDTOS =new ArrayList<RegisteredProductDTO>();
        for (RegisteredProduct registeredProduct:registeredProducts1){
            RegisteredProductDTO registeredProductDTO = new RegisteredProductDTO();
            registeredProductDTO.setCatalogue(registeredProduct.getProduct().getProductCatalogue().getName());
            registeredProductDTO.setId(registeredProduct.getId());
            registeredProductDTO.setCurrent_price(registeredProduct.getCurrentPrice());
            registeredProductDTO.setEnd_day(registeredProduct.getProduct().getEndDay());
            registeredProductDTO.setImg(registeredProduct.getProduct().getImg());
            registeredProductDTO.setName_product(registeredProduct.getProduct().getName());
            registeredProductDTOS.add(registeredProductDTO);
        }
        return registeredProductDTOS;
    }

    @Override
    public RegisteredProductDetailDTO getByIdRegisterProduct(Long id) {
       RegisteredProduct registeredProduct = registeredProductRepository.findById(id).orElse(null);
       RegisteredProductDetailDTO registeredProductDetailDTO =new RegisteredProductDetailDTO();
            registeredProductDetailDTO.setId(registeredProduct.getId());
            registeredProductDetailDTO.setProduct_id(registeredProduct.getProduct().getId());
            registeredProductDetailDTO.setName_product(registeredProduct.getProduct().getName());
            registeredProductDetailDTO.setContract_address(registeredProduct.getProduct().getContractAddress());
            registeredProductDetailDTO.setContract_phone_number(registeredProduct.getProduct().getContractPhoneNumber());
            registeredProductDetailDTO.setCurrent_price(registeredProduct.getCurrentPrice());
            registeredProductDetailDTO.setEnd_day(registeredProduct.getProduct().getEndDay());
            registeredProductDetailDTO.setImg(registeredProduct.getProduct().getImg());
            registeredProductDetailDTO.setMin_bet(registeredProduct.getProduct().getMinBet());
            registeredProductDetailDTO.setCatalogue(registeredProduct.getProduct().getProductCatalogue().getName());
            registeredProductDetailDTO.setProduct_info(registeredProduct.getProduct().getProductInfo());
            registeredProductDetailDTO.setStart_day(registeredProduct.getProduct().getStartDay());
            registeredProductDetailDTO.setStart_price(registeredProduct.getProduct().getStartPrice());
        return registeredProductDetailDTO;
    }

    @Override
    public List<RegisteredProduct> getAllRegisteredProductByName(String name) {
        return registeredProductRepository.findByProductNameContaining(name);
    }

//
}
