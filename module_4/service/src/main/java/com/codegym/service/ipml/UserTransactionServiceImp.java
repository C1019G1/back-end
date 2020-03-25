package com.codegym.service.ipml;

import com.codegym.dao.DTO.UserTransactionDTO;
import com.codegym.dao.entity.Auction;
import com.codegym.dao.entity.RegisteredProduct;
import com.codegym.dao.entity.UserTransaction;
import com.codegym.dao.repository.AuctionRepository;
import com.codegym.dao.repository.RegisteredProductRepository;
import com.codegym.dao.repository.UserTransactionRepository;
import com.codegym.service.UserTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserTransactionServiceImp implements UserTransactionService {
    @Autowired
    RegisteredProductRepository registeredProductRepository;
    @Autowired
    AuctionRepository auctionRepository;
    @Autowired
    UserTransactionRepository userTransactionRepository;

    @Override
    public void autoLoadingUserTransaction() {      // Tự động cập nhật record vào bảng UserTransaction
        Date nowDay = new Date();
        // Tìm kiếm RegisterProduct (endDay>nowDay)=> tìm kiếu Auction (registerProduc và BetPrice Max)
        List<RegisteredProduct> registeredProductList =registeredProductRepository.findAllByProductEndDayLessThan(nowDay);
        for (RegisteredProduct registeredProduct: registeredProductList){
            Long registerProductId = registeredProduct.getId();
            Date endDay = registeredProduct.getProduct().getEndDay();
            Calendar endDayCalendar = Calendar.getInstance();                       // khởi tạo
            endDayCalendar.setTime(endDay);                                        // chuyển Date sang kiểu Caladar
            endDayCalendar.add(Calendar.DAY_OF_MONTH,7);                   // công thêm 7 ngày
            Date period = endDayCalendar.getTime();                               // đổi sang lại kiểu Date

            Auction auction = auctionRepository.findFirstByRegisteredProductIdOrderByBetPriceDesc(registerProductId);
            UserTransaction userTransactionExit = userTransactionRepository.findByAuction(auction);   // kiểm tra sự tồn tại bản ghi
            if (auction !=null && userTransactionExit ==null){
                UserTransaction userTransaction = new UserTransaction();
                userTransaction.setAuction(auction);
                userTransaction.setFee(5000);
                userTransaction.setStatus(false);
                userTransaction.setPeriod(period);
                userTransactionRepository.save(userTransaction);
            }
        }
    }

    @Override
    public Page<UserTransactionDTO> getAllTransaction(Pageable pageable) {
        Page<UserTransactionDTO> userTransactionDTOS;
        Page<UserTransaction> userTransactions = userTransactionRepository.findAll(pageable);
        userTransactionDTOS = userTransactions.map(userTransaction -> {
            UserTransactionDTO userTransactionDTO = new UserTransactionDTO();
            userTransactionDTO.setId(userTransaction.getId());
            userTransactionDTO.setPeriod(userTransaction.getPeriod());
            userTransactionDTO.setSuccessTime(userTransaction.getAuction().getRegisteredProduct().getProduct().getEndDay());
            userTransactionDTO.setSeller(userTransaction.getAuction().getRegisteredProduct().getProduct().getUser().getUserProfile().getFullName());
            userTransactionDTO.setBuyer(userTransaction.getAuction().getUser().getUserProfile().getFullName());
            userTransactionDTO.setProductName(userTransaction.getAuction().getRegisteredProduct().getProduct().getName());
            userTransactionDTO.setPrice(userTransaction.getAuction().getBetPrice());
            userTransactionDTO.setFee(userTransaction.getFee());
            if(userTransaction.isStatus()) {
                userTransactionDTO.setStatus("Thành công");
            }else {
                userTransactionDTO.setStatus("Chưa thanh toán");
            }
            return userTransactionDTO;
        });
        return userTransactionDTOS;
    }

    @Override
    public Page<UserTransactionDTO> searchTransaction(Pageable pageable,String buyer, String seller, String productName, Date firstDate, Date lastDate,String status) {
        Page<UserTransactionDTO> userTransactionDTOS;
        Page<UserTransaction> userTransactions = null;
        switch (status){
            case "": userTransactions = userTransactionRepository.findAllByAuctionUserUserProfileFullNameContainingAndAuctionRegisteredProductProductUserUserProfileFullNameContainingAndAuctionRegisteredProductProductNameContainingAndAuctionRegisteredProductProductEndDayBetween(pageable,buyer,seller,productName,firstDate,lastDate);
                break;
            case "0":userTransactions = userTransactionRepository.findAllByAuctionUserUserProfileFullNameContainingAndAuctionRegisteredProductProductUserUserProfileFullNameContainingAndAuctionRegisteredProductProductNameContainingAndAuctionRegisteredProductProductEndDayBetweenAndStatus(pageable,buyer,seller,productName,firstDate,lastDate,false);
                break;
            case "1":userTransactions = userTransactionRepository.findAllByAuctionUserUserProfileFullNameContainingAndAuctionRegisteredProductProductUserUserProfileFullNameContainingAndAuctionRegisteredProductProductNameContainingAndAuctionRegisteredProductProductEndDayBetweenAndStatus(pageable,buyer,seller,productName,firstDate,lastDate,true);
                break;
            default:
        }
        userTransactionDTOS = userTransactions.map(userTransaction -> {
            UserTransactionDTO userTransactionDTO = new UserTransactionDTO();
            userTransactionDTO.setId(userTransaction.getId());
            userTransactionDTO.setPeriod(userTransaction.getPeriod());
            userTransactionDTO.setSuccessTime(userTransaction.getAuction().getRegisteredProduct().getProduct().getEndDay());
            userTransactionDTO.setSeller(userTransaction.getAuction().getRegisteredProduct().getProduct().getUser().getUserProfile().getFullName());
            userTransactionDTO.setBuyer(userTransaction.getAuction().getUser().getUserProfile().getFullName());
            userTransactionDTO.setProductName(userTransaction.getAuction().getRegisteredProduct().getProduct().getName());
            userTransactionDTO.setPrice(userTransaction.getAuction().getBetPrice());
            userTransactionDTO.setFee(userTransaction.getFee());
            if(userTransaction.isStatus()) {
                userTransactionDTO.setStatus("Đã thanh toán");
            }else {
                userTransactionDTO.setStatus("Chưa thanh toán");
            }
            return userTransactionDTO;
        });
        return userTransactionDTOS;
    }

    @Override
    public void deleteUserTransaction(Long idUserTransaction) {
        userTransactionRepository.deleteById(idUserTransaction);
    }
}
