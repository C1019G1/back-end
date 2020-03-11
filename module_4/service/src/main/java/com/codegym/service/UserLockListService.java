package com.codegym.service;

import com.codegym.dao.entity.UserLockList;

import com.codegym.dao.DTO.AdminUserLockListDTO;

public interface UserLockListService {
    boolean findByUserId(Long userId);
    boolean save(AdminUserLockListDTO adminUserLockListDTO);
    UserLockList checkStatus(Long userId);
}
