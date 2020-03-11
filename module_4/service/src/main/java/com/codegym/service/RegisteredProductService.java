package com.codegym.service;

import com.codegym.dao.DTO.RegisteredProductDTO;
import com.codegym.dao.DTO.RegisteredProductDetailDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Date;


public interface RegisteredProductService {
    RegisteredProductDetailDTO getByIdRegisterProduct(Long id);

    Page<RegisteredProductDTO> getAllRegisteredProduct(Pageable pageable, String catalogue, Date nowDay);

    Page<RegisteredProductDTO> getAllRegisteredProductByNamePriceCatalogue(Pageable pageable,String name, Long price1,Long price2, String catalogue, Date nowDay);

}
