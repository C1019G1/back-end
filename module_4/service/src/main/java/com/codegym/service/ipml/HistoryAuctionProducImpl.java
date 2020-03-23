package com.codegym.service.ipml;

import com.codegym.dao.entity.Product;
import com.codegym.dao.repository.HistoryAuctionProductRepository;
import com.codegym.service.HistoryAuctionProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HistoryAuctionProducImpl implements HistoryAuctionProductService {
    @Autowired
    HistoryAuctionProductRepository historyAuctionProductRepository;


    @Override
    public List<Product> findAuctionProductByUserId(Long userId, Pageable pageable) {
        return historyAuctionProductRepository.findProductByUserId(userId,pageable);
    }
}
