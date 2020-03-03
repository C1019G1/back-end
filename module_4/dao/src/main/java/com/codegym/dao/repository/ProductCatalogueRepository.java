package com.codegym.dao.repository;

import com.codegym.dao.entity.Product;
import com.codegym.dao.entity.ProductCatalogue;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCatalogueRepository extends PagingAndSortingRepository<ProductCatalogue,Long> {
}
