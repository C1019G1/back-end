package com.codegym.service;

import com.codegym.dao.DTO.AdminUserProfileDTO;
import com.codegym.dao.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface UserService {
    Page<User> getAllUser(Pageable pageable);
    List<User> getAllUser();
    void save (User user);
    Page<AdminUserProfileDTO> getUserProfileAdmin(Pageable pageable);
}
