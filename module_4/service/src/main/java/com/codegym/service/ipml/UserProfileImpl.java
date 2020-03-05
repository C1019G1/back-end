package com.codegym.service.ipml;

import com.codegym.dao.entity.UserProfile;
import com.codegym.dao.repository.UserProfileRepository;
import com.codegym.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProfileImpl implements UserProfileService {
    @Autowired
    public UserProfileRepository userProfileRepository;


    @Override
    public UserProfile findAllProfileUser(Long id) {
        return userProfileRepository.getUserProfileById(id);
    }
}
