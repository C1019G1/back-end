package com.codegym.dao.repository;

import com.codegym.dao.entity.UserLoginHistory;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserLoginHistoryRepository extends PagingAndSortingRepository<UserLoginHistory,Long> {
    UserLoginHistory findFirstByUserIdOrderByLoginTimeDesc(Long userId);
}
