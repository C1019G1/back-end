package com.codegym.service;

import com.codegym.dao.DTO.AdminProductDetailDTO;
import com.codegym.dao.DTO.AdminProductManagerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminProductManagerService {
    Page<AdminProductManagerDTO> getAllProduct(Pageable pageable);
    Page<AdminProductManagerDTO> getAllProductByNameProductAndCatalogueAndUserNameAndStartPriceAndStatus(String name, String catalogueName, String userName, Long startPrice1 , Long startPrice2, Boolean status, Pageable pageable);
    AdminProductDetailDTO getByIdProduct(Long id);
}
