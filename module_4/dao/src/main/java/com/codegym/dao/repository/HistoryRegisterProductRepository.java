package com.codegym.dao.repository;

import com.codegym.dao.DTO.HistoryRegisterProductDTO;
import com.codegym.dao.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository

public interface HistoryRegisterProductRepository extends JpaRepository<Product, Long> {
    List <Product> findAllById (Pageable pageable, Long id);

}
