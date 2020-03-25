package com.codegym.dao.repository;

import com.codegym.dao.entity.Auction;
import com.codegym.dao.entity.UserTransaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.Date;

@Repository
public interface UserTransactionRepository extends PagingAndSortingRepository<UserTransaction, Long> {
    UserTransaction findByAuction(Auction auction); // Để kiểm tra có tồn tại bản nghi trước khi loading vào bảng

    Page<UserTransaction> findAllByAuctionUserUserProfileFullNameContainingAndAuctionRegisteredProductProductUserUserProfileFullNameContainingAndAuctionRegisteredProductProductNameContainingAndAuctionRegisteredProductProductEndDayBetweenAndStatus(Pageable pageable,String buyer,String seller,String productName,Date firstDate,Date lastDate,Boolean status);
    Page<UserTransaction> findAllByAuctionUserUserProfileFullNameContainingAndAuctionRegisteredProductProductUserUserProfileFullNameContainingAndAuctionRegisteredProductProductNameContainingAndAuctionRegisteredProductProductEndDayBetween(Pageable pageable,String buyer,String seller,String productName,Date firstDate,Date lastDate);
    Page<UserTransaction> findAllByAuctionUserUserProfileFullNameContainingAndAuctionRegisteredProductProductUserUserProfileFullNameContainingAndAuctionRegisteredProductProductNameContainingAndStatus(Pageable pageable,String buyer,String seller,String productName,Boolean status);

    Page<UserTransaction> findAllByAuctionRegisteredProductProductEndDayBetween(Pageable pageable, Date firstDate, Date lastDate );
}
