package com.codegym.service.ipml;

import com.codegym.dao.DTO.AdminUserProfileDTO;
import com.codegym.dao.DTO.UserRegisterDTO;
import com.codegym.dao.entity.Role;
import com.codegym.dao.entity.User;
import com.codegym.dao.entity.UserProfile;
import com.codegym.dao.repository.UserRepository;
import com.codegym.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserLockListService lockListService;
    @Autowired
    UserLoginHistoryService loginHistoryService;
    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Autowired
    UserRankService userRankService;
    @Autowired
    UserProfileService userProfileService;
    @Autowired
    RoleService roleService;
    public AdminUserProfileDTO getUserProfileDTOByEmail(String email) {
        User user = this.userRepository.findByUserProfile_Email(email).orElse(null);
        if (user != null) {
            return this.getAdminUserProfileDTO(user);
        }
        return null;
    }

    public AdminUserProfileDTO getUserProfileDTOById(Long id) {
        User user = this.userRepository.findById(id).orElse(null);
        if (user != null) {
            return this.getAdminUserProfileDTO(user);
        }
        return null;
    }

    public AdminUserProfileDTO getUserProfileDTOByIdAndEmail(Long id, String email) {
        User user = this.userRepository.findByIdAndUserProfile_Email(id, email).orElse(null);
        if (user != null) {
            return this.getAdminUserProfileDTO(user);
        }
        return null;
    }

    public Page<AdminUserProfileDTO> getUsersProfileByNameByRank(Pageable pageable, String name, String rankName) {
        Page<User> users = userRepository.findAllByUserProfile_FullNameContainingIgnoreCaseAndUserProfile_Rank_NameContainingIgnoreCase(pageable, name, rankName);
        return getUserProfileDTOS(users);
    }

    @Override
    @Transactional //phải có anotation này , nếu không thì jps không thể get được Role của user
    public UserDetails loadUserByUsername(String username) {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                grantedAuthorities);
    }

    public User save(UserRegisterDTO userRegisterDTO) {
        UserProfile userProfile = new UserProfile();
        userProfile.setFullName(userRegisterDTO.getFullName());
        userProfile.setEmail(userRegisterDTO.getEmail());
        userProfile.setAddress(userRegisterDTO.getAddress());
        userProfile.setIdentityNumber(userRegisterDTO.getIdentityNumber());
        userProfile.setPhone(userRegisterDTO.getPhone());
        userProfile.setRank(userRegisterDTO.getRank());
        userProfile.setDayOfBirth(userRegisterDTO.getDayOfBirth());
        if (userRegisterDTO.getRank()==null){
            userProfile.setRank(userRankService.getById((long) 4));
        } else {
            userProfile.setRank(userRankService.getById(userRegisterDTO.getRank().getId()));
        }

        userProfileService.save(userProfile);

        User user = new User();
        user.setUserName(userRegisterDTO.getUserName());
        user.setPassword(userRegisterDTO.getPassword());
        user.setUserProfile(userProfile);

        Set<Role> userRole =new HashSet<>();
        userRole.add(roleService.findByid((long) 3));
        user.setRoles(userRole);
        user.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findByUserName(String username) {
        return userRepository.findByUserName(username);
    }

    private Page<AdminUserProfileDTO> getUserProfileDTOS(Page<User> users) {
        return users.map(  this::getAdminUserProfileDTO);
    }

    private AdminUserProfileDTO getAdminUserProfileDTO(User user) {
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
    }
    public boolean checkUsernameIsExisted(String username){
        return userRepository.findByUserName(username)!=null;
    }
}
