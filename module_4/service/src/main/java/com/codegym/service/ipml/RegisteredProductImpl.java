package com.codegym.service.ipml;

import com.codegym.dao.entity.RegisteredProduct;
import com.codegym.dao.repository.RegisteredProductRepository;
import com.codegym.service.RegisteredProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RegisteredProductImpl implements RegisteredProductService {
    @Autowired
    RegisteredProductRepository registeredProductRepository;
    @Override
    public List<RegisteredProduct> getAllRegisteredProduct() {
        return (List<RegisteredProduct>) registeredProductRepository.findAll();
    }
}
