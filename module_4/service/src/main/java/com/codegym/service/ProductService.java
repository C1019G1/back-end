package com.codegym.service;

import com.codegym.dao.entity.Product;

public interface ProductService {
    Product save(Product product);
    Product findById(Long id);

    String getNameUserByProductId(Long productId);
}
