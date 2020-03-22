package com.codegym.service.ipml;

import com.codegym.dao.DTO.AuctionDTO;
import com.codegym.dao.entity.Auction;
import com.codegym.dao.repository.AuctionRepository;
import com.codegym.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Override
    public List<AuctionDTO> findTop5(Long id) {
        List<Auction> auctionList = auctionRepository.findTop5ByRegisteredProductIdOrderByBetTimeDesc(id);
        List<AuctionDTO> auctionDTOList = new ArrayList<>();
        for (Auction auction :
                auctionList) {
            AuctionDTO auctionDTO = new AuctionDTO();
            auctionDTO.setBetPrice(auction.getBetPrice());
            auctionDTO.setBetTime(auction.getBetTime());
            auctionDTO.setUserName(auction.getUser().getUserName());
            auctionDTOList.add(auctionDTO);
        }
        return auctionDTOList;
    }
}
