package com.codegym.service.ipml;

import com.codegym.dao.entity.UserLoginHistory;
import com.codegym.dao.repository.UserLoginHistoryRepository;
import com.codegym.service.UserLoginHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UserLoginHistoryImpl implements UserLoginHistoryService {
    @Autowired
    UserLoginHistoryRepository loginHistoryRepository;

    @Override
    public Optional<Date> findLastLoginByUserId(Long userId) {
        UserLoginHistory userLoginHistory=loginHistoryRepository.findFirstByUserIdOrderByLoginTimeDesc(userId);
        Optional<Date> date;
        if (userLoginHistory==null)
            date=Optional.empty();
        else
            date= Optional.ofNullable((userLoginHistory.getLoginTime()));
        return date ;
    }
}
