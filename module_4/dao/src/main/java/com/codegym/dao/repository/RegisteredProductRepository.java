package com.codegym.dao.repository;


import com.codegym.dao.entity.RegisteredProduct;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisteredProductRepository extends PagingAndSortingRepository<RegisteredProduct,Long> { }
