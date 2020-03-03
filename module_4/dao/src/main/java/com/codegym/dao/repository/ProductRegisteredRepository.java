package com.codegym.dao.repository;


import com.codegym.dao.entity.ProductRegistered;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRegisteredRepository extends PagingAndSortingRepository<ProductRegistered,Long> {
}
