package com.codegym.dao.repository;

import com.codegym.dao.entity.UserLockList;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLockListRepository extends PagingAndSortingRepository<UserLockList,Long> {
}
