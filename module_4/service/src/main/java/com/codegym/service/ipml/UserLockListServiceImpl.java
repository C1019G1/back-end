package com.codegym.service.ipml;

import com.codegym.dao.entity.UserLockList;
import com.codegym.dao.repository.UserLockListRepository;
import com.codegym.service.UserLockListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

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

    @Override
    public UserLockList checkStatus(Long userId) {
        try {
            UserLockList userlock = userLockListRepository.findFirstByUserIdOrderByDayLockEndDesc(userId);
            Date lockEndDay  = userlock.getDayLockEnd();
            Date currentTime  = new Date();
            if (lockEndDay.compareTo(currentTime) > 0) {
                return userlock;
            }
            return null;
        }
        catch (Exception e){
            return null;
        }
    }
}
