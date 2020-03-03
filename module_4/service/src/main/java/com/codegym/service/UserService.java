package com.codegym.service;

import com.codegym.dao.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    List<User> getAllUser();
}
