package com.codegym.service;
import com.codegym.dao.DTO.TransactionDTO;
import com.codegym.dao.DTO.UserTransactionDTO;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface UserTransactionService {
    void autoLoadingUserTransaction();
    Page<UserTransactionDTO> getAllTransaction(Pageable pageable);

    Page<UserTransactionDTO> searchTransaction(Pageable pageable,String buyer, String seller, String productName,Date firstDate, Date lastDate,Boolean status);
    List<TransactionDTO> getAllByUser (String userName);
}
