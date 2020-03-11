package com.codegym.service.ipml;

import com.codegym.dao.DTO.AdminUserLockListDTO;
import com.codegym.dao.entity.User;
import com.codegym.dao.entity.UserLockList;
import com.codegym.dao.repository.UserLockListRepository;
import com.codegym.dao.repository.UserRepository;
import com.codegym.service.UserLockListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserLockListServiceImpl implements UserLockListService {
    @Autowired
    UserLockListRepository userLockListRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public boolean findByUserId(Long userId) {
        return (userLockListRepository.findById(userId).orElse(null) != null);
    }

    @Override
    public boolean save(AdminUserLockListDTO adminUserLockListDTO) {
        List<Long> usersId = adminUserLockListDTO.getUsers();
        if (usersId != null) {
            for (Long id : usersId
            ) {
                User user = this.userRepository.findById(id).orElse(null);
                if (user != null) {
                    UserLockList userLockList = new UserLockList();
                    userLockList.setReasonLock(adminUserLockListDTO.getReasonLock());
                    userLockList.setDayLockStart(adminUserLockListDTO.getDayLockStart());
                    userLockList.setDayLockEnd(adminUserLockListDTO.getDayLockEnd());
                    userLockList.setUser(user);
                    userLockListRepository.save(userLockList);
                }
            }
            return true;
        } else
            return false;
    }
}
