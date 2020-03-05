package com.codegym.service.ipml;

import com.codegym.dao.entity.UserLockList;
import com.codegym.dao.repository.UserLockListRepository;
import com.codegym.service.UserLockListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserLockListServiceImpl implements UserLockListService {
    @Autowired
    UserLockListRepository userLockListRepository;
    @Override
    public boolean findByUserId(Long userId) {
        if(userLockListRepository.findById(userId).orElse(null)!=null){
            return true;
        }
        return false;
    }
}
