package com.codegym.dao.repository;


import com.codegym.dao.entity.RegisteredProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisteredProductRepository extends PagingAndSortingRepository<RegisteredProduct,Long> {
    @Query(value = "SELECT p.id PId, p.name, p.end_day, p.img, rp.current_price, rp.id PrId\n" +
            "FROM registered_product rp\n" +
            "INNER JOIN product p ON rp.product_id = p.id\n" +
            "WHERE TIMESTAMPDIFF(SECOND, NOW(), p.end_day) >= 0;", nativeQuery = true)
    List<RegisteredProduct> getAllByProductEndDay();
}
