package com.codegym.dao.repository;

import com.codegym.dao.entity.UserRank;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.awt.*;

public interface UserRankRepository extends PagingAndSortingRepository<UserRank,Long> {
}
