package com.codegym.dao.repository;

import com.codegym.dao.entity.Product;
import com.codegym.dao.entity.ProductCatalogue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

   Page<Product> findAllByNameContainingAndProductCatalogueContainingAndUserUserNameContainingAndStartPriceContainingAndPendingStatusTrue(String name, ProductCatalogue productCatalogue, String user_userName, Long startPrice, Pageable pageable);
}
//(String name, ProductCatalogue productCatalogue, String user_userName, Long startPrice, boolean ending_status, Pageable pageable)