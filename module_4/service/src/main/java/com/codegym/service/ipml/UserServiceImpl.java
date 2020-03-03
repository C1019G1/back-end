package com.codegym.service.ipml;

import com.codegym.dao.entity.User;
import com.codegym.dao.repository.UserRepository;
import com.codegym.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public Page<User> getAllUser(Pageable pageable) {
        return (Page<User>) userRepository.findAll();
    }

    @Override
    public List<User> getAllUser() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
