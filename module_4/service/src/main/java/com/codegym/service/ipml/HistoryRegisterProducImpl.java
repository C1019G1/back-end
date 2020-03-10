package com.codegym.service.ipml;

import com.codegym.dao.DTO.HistoryRegisterProductDTO;
import com.codegym.dao.entity.Product;
import com.codegym.dao.entity.User;
import com.codegym.dao.repository.HistoryRegisterProductRepository;
import com.codegym.service.HistoryRegisterProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Service
public class HistoryRegisterProducImpl implements HistoryRegisterProductService {
    @Autowired
    HistoryRegisterProductRepository historyRegisterProductRepository;

    @Override
    public Page<HistoryRegisterProductDTO> getAllHistoryRegisterProduct(Pageable pageable, Long id) {

       return null;
    }


}
