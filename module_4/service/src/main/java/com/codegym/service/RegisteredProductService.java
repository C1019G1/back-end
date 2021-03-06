package com.codegym.service;

import com.codegym.dao.DTO.RegisteredProductDTO;
import com.codegym.dao.DTO.RegisteredProductDetailDTO;
import com.codegym.dao.entity.RegisteredProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Date;
import java.util.List;


public interface RegisteredProductService {
    RegisteredProductDetailDTO getByIdRegisterProduct(Long id);

    Page<RegisteredProductDTO> getAllRegisteredProduct(Pageable pageable, String catalogue, Date nowDay);

    Page<RegisteredProductDTO> getAllRegisteredProductByNamePriceCatalogue(Pageable pageable,String name, Long price1,Long price2, String catalogue, Date nowDay);

    List<RegisteredProduct> getAllRegisterProductByEnday(Date nowDay);
}
