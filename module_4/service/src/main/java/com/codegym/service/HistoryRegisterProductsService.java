package com.codegym.service;

import com.codegym.dao.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;

public interface HistoryRegisterProductsService {
    Page<Product> findProductByUserId( Pageable pageable, Long id);
}
