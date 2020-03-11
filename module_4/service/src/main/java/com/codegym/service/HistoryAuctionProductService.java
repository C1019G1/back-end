package com.codegym.service;


import com.codegym.dao.DTO.HistoryRegisterProductDTO;
import com.codegym.dao.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HistoryAuctionProductService {
  List<Product> findAuctionProductByUserId(Long userId, Pageable pageable);
}
