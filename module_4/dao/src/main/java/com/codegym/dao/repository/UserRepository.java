package com.codegym.dao.repository;

import com.codegym.dao.entity.User;
import com.codegym.dao.entity.UserProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User,Long> {
    User findByUserName(String userName);
    Page<User> findAllByUserProfile_FullNameContainingIgnoreCaseAndUserProfile_Rank_NameContainingIgnoreCase(Pageable pageable,String name,String rankName);
    Optional<User> findByUserProfile_Email(String email);
    Optional<User> findByIdAndUserProfile_Email(Long id,String email);
}
