package com.codegym.service;

import com.codegym.dao.DTO.AdminUserLockListDTO;

public interface UserLockListService {
    boolean findByUserId(Long userId);
    boolean save(AdminUserLockListDTO adminUserLockListDTO);
}
