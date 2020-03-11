package com.codegym.service.ipml;

import com.codegym.dao.entity.User;
import com.codegym.dao.entity.UserLoginHistory;
import com.codegym.dao.repository.UserLoginHistoryRepository;
import com.codegym.service.UserLoginHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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

    @Override
    public void saveNewLoginSession(User user) {
        UserLoginHistory userLoginHistory = new UserLoginHistory();
        userLoginHistory.setUser(user);
        userLoginHistory.setLoginTime(new Date());
        loginHistoryRepository.save(userLoginHistory);
    }

    @Override
    public Set<UserLoginHistory> findAllByUser(User user) {
        return loginHistoryRepository.findAllByUser(user);
    }

    @Override
    public List<String> getAllLoginTime(User user) {
        return loginHistoryRepository.findLoginTimeByUser(user);
    }
}
