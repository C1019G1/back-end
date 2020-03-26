package com.codegym.service;

import com.codegym.dao.entity.User;
import com.codegym.dao.entity.UserProfile;

public interface UserProfileService {
   UserProfile findAllProfileUser(Long id);
   UserProfile save(UserProfile userProfile);
   UserProfile getUserProfileByEmail(String email);
   boolean checkEmailIsExisted(String email);
   UserProfile findById(Long id);
   void editUserProfile ( UserProfile userProfile);
}
