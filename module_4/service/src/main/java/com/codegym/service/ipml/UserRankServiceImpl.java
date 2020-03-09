package com.codegym.service.ipml;

import com.codegym.dao.entity.UserRank;
import com.codegym.dao.repository.UserRankRepository;
import com.codegym.service.UserRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserRankServiceImpl implements UserRankService {
    @Autowired
    UserRankRepository userRankRepository;
    @Override
    public Iterable<UserRank> getAllRank() {
        return userRankRepository.findAll();
    }

    @Override
    public UserRank getById(Long id) {
        return userRankRepository.findById(id).orElse(null);
    }
}
