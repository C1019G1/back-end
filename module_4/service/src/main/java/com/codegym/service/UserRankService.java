package com.codegym.service;

import com.codegym.dao.entity.UserRank;


public interface UserRankService {
    Iterable<UserRank> getAllRank();
    UserRank getById(Long id);
}
