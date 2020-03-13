package com.codegym.service.ipml;

import com.codegym.dao.entity.Product;
import com.codegym.dao.repository.HistoryRegisterProductRepository;
import com.codegym.service.HistoryRegisterProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class HistoryRegisterProductsServiceImpl implements HistoryRegisterProductsService {
    @Autowired
    private HistoryRegisterProductRepository historyRegisterProductRepository;


    @Override
    public Page<Product> findProductByUserId(Pageable pageable, Long id) {
        return historyRegisterProductRepository.findAllByUserId(pageable, id);
    }
}
