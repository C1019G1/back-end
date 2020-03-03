package com.codegym.dao.repository;

import com.codegym.dao.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends PagingAndSortingRepository<User,Long> {
}
