package com.codegym.dao.repository;


import com.codegym.dao.DTO.RegisteredProductDTO;
import com.codegym.dao.entity.RegisteredProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RegisteredProductRepository extends PagingAndSortingRepository<RegisteredProduct,Long> {
    Page<RegisteredProduct> findAllByProductNameContainingAndCurrentPriceBetweenAndProductProductCatalogueNameContainingAndProductEndDayGreaterThan(Pageable pageable,String name,Long price1,Long price2, String catalogue, Date nowDay);
    Page<RegisteredProduct> findAllByProductProductCatalogueNameContainingAndProductEndDayGreaterThan(Pageable pageable, String catalogue, Date nowDay);
}
