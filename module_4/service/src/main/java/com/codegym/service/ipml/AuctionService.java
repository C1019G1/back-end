package com.codegym.service.ipml;

        import com.codegym.dao.entity.Auction;

        import java.util.List;

public interface AuctionService {
    List<Auction>  findAuctionById(Long id);
}
