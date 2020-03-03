package com.codegym.dao.repository;

import com.codegym.dao.entity.User;
import com.codegym.dao.entity.UserProfile;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserProfileRepository extends PagingAndSortingRepository<UserProfile,Long> {
}
