package com.codegym.service;

import com.codegym.dao.DTO.RegisteredProductDTO;
import com.codegym.dao.DTO.RegisteredProductDetailDTO;
import com.codegym.dao.entity.RegisteredProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RegisteredProductService {
    List<RegisteredProductDTO> getAllRegisteredProductEndDay();
    RegisteredProductDetailDTO getByIdRegisterProduct(Long id);

    List<RegisteredProduct> getAllRegisteredProductByName(String name);
}
