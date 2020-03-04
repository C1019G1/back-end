package com.codegym.service.ipml;

import com.codegym.dao.DTO.AdminUserProfileDTO;
import com.codegym.dao.entity.User;
import com.codegym.dao.repository.UserRepository;
import com.codegym.service.UserLockListService;
import com.codegym.service.UserLoginHistoryService;
import com.codegym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserLockListService lockListService;
    @Autowired
    UserLoginHistoryService loginHistoryService;

    @Override
    public Page<User> getAllUser(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public List<User> getAllUser() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public Page<AdminUserProfileDTO> getUserProfileAdmin(Pageable pageable) {
        Page<User> users =  getAllUser(pageable);
        Page<AdminUserProfileDTO> userProfileDTOS = users.map(user -> {
            AdminUserProfileDTO userProfileDTO = new AdminUserProfileDTO();
            userProfileDTO.setId(user.getId());
            userProfileDTO.setAddress(user.getUserProfile().getAddress());
            userProfileDTO.setFullName(user.getUserProfile().getFullName());
            userProfileDTO.setEmail(user.getUserProfile().getEmail());
            userProfileDTO.setPhoneNumber(user.getUserProfile().getPhone());
            userProfileDTO.setContributePoint(user.getUserProfile().getContributePoint());
            userProfileDTO.setRank(user.getUserProfile().getRank().getName());
            userProfileDTO.setLastLogin(this.loginHistoryService.findLastLoginByUserId(user.getId()));
            userProfileDTO.setStatus(this.lockListService.findByUserId(user.getId()));
            return userProfileDTO;
        });
        return userProfileDTOS;
    }
}
