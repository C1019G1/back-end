package com.codegym.service;

import com.codegym.dao.DTO.AuctionDTO;
import com.codegym.dao.entity.Auction;

import java.util.List;

public interface AuctionService {
    List<Auction> findAuctionById(Long id);
    Auction findCurrentPriceById(Long id);
    List<AuctionDTO> findTop5(Long id);
    boolean save (AuctionDTO auctionDTO, Long id);
}
