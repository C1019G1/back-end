package com.codegym.dao.repository;

import com.codegym.dao.entity.User;
import com.codegym.dao.entity.UserLoginHistory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface UserLoginHistoryRepository extends PagingAndSortingRepository<UserLoginHistory,Long> {
    UserLoginHistory findFirstByUserIdOrderByLoginTimeDesc(Long userId);
    Set<UserLoginHistory> findAllByUser(User user);
   // List<Date>  findDistinctByLoginTimeByUser(User user);
   @Modifying
   @Query("select u.loginTime from UserLoginHistory u where u.user = :user ")
    List<String>  findLoginTimeByUser(@Param("user")User user);
}
