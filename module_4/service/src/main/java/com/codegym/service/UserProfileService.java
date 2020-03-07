package com.codegym.service;

import com.codegym.dao.entity.UserProfile;

public interface UserProfileService {
   UserProfile findAllProfileUser(Long id);
   UserProfile findById(Long id);
   void editUserProfile ( UserProfile userProfile);
}
