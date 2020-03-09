package com.codegym.service.ipml;

import com.codegym.dao.DTO.AdminUserProfileDTO;
import com.codegym.dao.DTO.UserDTO;
import com.codegym.dao.entity.Role;
import com.codegym.dao.entity.User;
import com.codegym.dao.repository.UserRepository;
import com.codegym.service.UserLockListService;
import com.codegym.service.UserLoginHistoryService;
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

    public Page<User> getAllUser(Pageable pageable) {
        return userRepository.findAll(pageable);
    }


    public List<User> getAllUser() {
        return (List<User>) userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }
    public AdminUserProfileDTO getUserProfileDTOByEmail(String email){
        User user= this.userRepository.findByUserProfile_Email(email).orElse(null);
        if(user!=null){
            return this.getAdminUserProfileDTO(user);
        }
        return null;
    }
    public AdminUserProfileDTO getUserProfileDTOById(Long id){
        User user= this.userRepository.findById(id).orElse(null);
        if (user!=null){
            return this.getAdminUserProfileDTO(user);
        }
        return null;
    }
    public AdminUserProfileDTO getUserProfileDTOByIdAndEmail(Long id, String email){
        User user= this.userRepository.findByIdAndUserProfile_Email(id,email).orElse(null);
        if (user!=null){
            return this.getAdminUserProfileDTO(user);
        }
        return null;
    }
    public Page<AdminUserProfileDTO> getUsersProfileByNameByRank(Pageable pageable,String name, String rankName) {
        Page<User> users =  userRepository.findAllByUserProfile_FullNameContainingIgnoreCaseAndUserProfile_Rank_NameContainingIgnoreCase(pageable,name,rankName);
        return getUserProfileDTOS(users);
    }
    @Override
    @Transactional //phải có anotation này , nếu không thì jps không thể get được Role của user
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        Set<Role> roles = user.getRoles();
        for(Role role: roles){
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                grantedAuthorities);
    }

    public User save(UserDTO user) {
        User newUser = new User();
        newUser.setUserName(user.getUsername());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userRepository.save(newUser);
    }

    private Page<AdminUserProfileDTO> getUserProfileDTOS(Page<User> users) {
        return users.map(this::getAdminUserProfileDTO);
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

}
