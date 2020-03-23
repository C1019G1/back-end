package com.codegym.dao.repository;

import com.codegym.dao.entity.Auction;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AuctionRepository extends PagingAndSortingRepository<Auction, Long> {
 List<Auction> findTop5ByRegisteredProductIdOrderByBetTimeDesc(Long id);
 Auction findFirstByRegisteredProductIdOrderByBetPriceDesc(Long id);
}
