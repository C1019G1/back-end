package com.codegym.service;

import com.codegym.dao.entity.User;
import com.codegym.dao.entity.UserLoginHistory;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserLoginHistoryService {
    Optional<Date> findLastLoginByUserId(Long userId);
    void saveNewLoginSession(User user);
    Set<UserLoginHistory> findAllByUser(User user);
    List<String> getAllLoginTime(User user);
}
