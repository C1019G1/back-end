package com.codegym.service.ipml;

import com.codegym.dao.entity.RegisteredProduct;
import com.codegym.dao.repository.RegisteredProductRepository;
import com.codegym.service.RegisteredProductService;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisteredProductImpl implements RegisteredProductService {
    @Autowired
    RegisteredProductRepository registeredProductRepository;
    @Override
    public List<RegisteredProduct> getAllRegisteredProductEndDay() {
        return registeredProductRepository.getAllByProductEndDay();
    }
}
