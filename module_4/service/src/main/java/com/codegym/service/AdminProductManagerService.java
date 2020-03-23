package com.codegym.service;

import com.codegym.dao.DTO.AdminProductDetailDTO;
import com.codegym.dao.DTO.AdminProductManagerDTO;
import com.codegym.dao.entity.ProductCatalogue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminProductManagerService {
    Page<AdminProductManagerDTO> getAllProduct(Pageable pageable);
    Page<AdminProductManagerDTO> getAllProductByNameProductAndCatalogueAndUserNameAndStartPriceAndStatus(String name, ProductCatalogue productCatalogue, String user_userName, Long startPrice, boolean status, Pageable pageable);
    AdminProductDetailDTO getByIdProduct(Long id);
}
