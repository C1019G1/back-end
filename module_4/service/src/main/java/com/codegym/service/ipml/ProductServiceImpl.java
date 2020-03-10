package com.codegym.service.ipml;

import com.codegym.dao.entity.Product;
import com.codegym.dao.repository.ProductRepository;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> findProductByUserId(Long userId, Pageable pageable) {
        return productRepository.findProductByUserId(userId,pageable);
    }

}
