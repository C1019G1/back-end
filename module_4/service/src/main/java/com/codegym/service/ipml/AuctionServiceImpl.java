package com.codegym.service.ipml;

import com.codegym.dao.DTO.AuctionDTO;
import com.codegym.dao.entity.Auction;
import com.codegym.dao.entity.RegisteredProduct;
import com.codegym.dao.entity.User;
import com.codegym.dao.repository.AuctionRepository;
import com.codegym.dao.repository.RegisteredProductRepository;
import com.codegym.dao.repository.UserRepository;
import com.codegym.service.AuctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuctionServiceImpl implements AuctionService {
    @Autowired
    AuctionRepository auctionRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RegisteredProductRepository registeredProductRepository;
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

    @Override
    public boolean save(AuctionDTO auctionDTO, Long id) {
        try {
            Auction auction= new Auction();
            User user = userRepository.findByUserName(auctionDTO.getUserName());
            RegisteredProduct registeredProduct = registeredProductRepository.findById(id).orElse(null);
            auction.setBetPrice(auctionDTO.getBetPrice());
            auction.setBetTime(auctionDTO.getBetTime());
            auction.setUser(user);
            auction.setRegisteredProduct(registeredProduct);
            return this.auctionRepository.save(auction) != null;
        } catch (NullPointerException e){
            return false;
        }
    }
}
