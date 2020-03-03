package com.codegym.dao.repository;

import com.codegym.dao.entity.ProductAuction;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductAuctionRepository extends PagingAndSortingRepository<ProductAuction, Long> {
}
