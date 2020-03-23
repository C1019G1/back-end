package com.codegym.service;

import com.codegym.dao.entity.Auction;

import java.util.Date;
import java.util.List;

public interface AuctionService {
    List<Auction> findAuctionById(Long id);
}
