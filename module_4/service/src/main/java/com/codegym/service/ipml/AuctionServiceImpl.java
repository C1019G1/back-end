package com.codegym.service.ipml;

import com.codegym.dao.entity.Auction;
import com.codegym.dao.repository.AuctionRepository;
import com.codegym.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctionServiceImpl implements AuctionService {
    @Autowired
    AuctionRepository auctionRepository;

    @Override
    public List<Auction> findAuctionById(Long id) {
        return auctionRepository.findTop5ByRegisteredProductIdOrderByBetTimeDesc(id);
    }

    @Override
    public Auction findCurrentPriceById(Long id) {
        return auctionRepository.findFirstByRegisteredProductIdOrderByBetPriceDesc(id);
    }
}
