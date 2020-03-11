package com.codegym.service;

import com.codegym.dao.entity.UserLockList;

public interface UserLockListService {
    boolean findByUserId(Long userId);
    UserLockList checkStatus(Long userId);
}
