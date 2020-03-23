package com.codegym.dao.repository;

import com.codegym.dao.entity.ProductCatalogue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CatalogueRepository extends JpaRepository<ProductCatalogue,Long> {
    ProductCatalogue findByName(String name);
}
