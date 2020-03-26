package com.codegym.service.ipml;

import com.codegym.dao.entity.Product;
import com.codegym.dao.repository.ProductRepository;
import com.codegym.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public String getNameUserByProductId(Long productId) {
        Product product = productRepository.findById(productId).orElse(null);
        if (product!=null){
            return product.getUser().getUserProfile().getFullName();
        }else {
            return "Sản phẩm xác định người đăng";
        }

    }
}
