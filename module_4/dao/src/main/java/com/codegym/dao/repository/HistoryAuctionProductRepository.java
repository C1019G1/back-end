package com.codegym.dao.repository;

import com.codegym.dao.entity.Product;
import org.hibernate.validator.constraints.EAN;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HistoryAuctionProductRepository extends JpaRepository<Product, Long> {
    @Query(value = "SELECT * from product Where user_id =:userId ",
            nativeQuery = true)
    List<Product> findProductByUserId(@Param("userId") Long userId, @Param("page") Pageable page);
}
