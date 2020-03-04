package com.codegym.service;

import com.codegym.dao.entity.RegisteredProduct;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RegisteredProductService {
    List<RegisteredProduct> getAllRegisteredProductEndDay();
}
