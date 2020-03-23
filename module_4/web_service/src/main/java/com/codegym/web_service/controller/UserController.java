package com.codegym.web_service.controller;

import com.codegym.common.RandomString;
import com.codegym.common.SendGmailService;
import com.codegym.dao.DTO.*;
import com.codegym.dao.entity.*;
import com.codegym.service.*;
import com.codegym.service.ipml.UserServiceImpl;
import com.codegym.web_service.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.IconUIResource;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.codegym.dao.DTO.UseProfileDTO;
import com.codegym.dao.entity.UserProfile;
import com.codegym.dao.repository.UserProfileRepository;
import com.codegym.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "Authorization")
@RequestMapping("")
public class UserController {

    @Autowired
    UserServiceImpl userService;
    @Autowired
    UserProfileService userProfileService;
    @Autowired(required = false)
    AuthenticationManager authenticationManager;
    @Autowired
    JwtTokenUtil jwtTokenUtil;
    @Autowired
    UserLoginHistoryService userLoginHistoryService;
    @Autowired
    UserLockListService userLockListService;
    @Autowired
    CatalogueService catalogueService;
    @Autowired
    ProductService productService;
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> saveUser(@RequestBody UserRegisterDTO userRegisterDTO) {
        // kiểm tra username hoặc email đã tồn tại trong database?
        if (userService.checkUsernameIsExisted(userRegisterDTO.getUserName())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Tài khoản đã tồn tại");
        }
        if (userProfileService.checkEmailIsExisted(userRegisterDTO.getEmail())) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("email này đã được đăng ký");
        }
        return ResponseEntity.ok(userService.save(userRegisterDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        UserDetails userDetails = null;
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDTO.getUsername(), userDTO.getPassword())
            );
            userDetails = userService
                    .loadUserByUsername(authentication.getName());
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Thông tin đăng nhập không chính xác");
        }
        String jwtToken = jwtTokenUtil.generateToken(userDetails);
        String username = userDetails.getUsername();
        String rolename = getMaxRoleName(username);

        User user = userService.findByUserName(username);
        UserLockList userlock = userLockListService.checkStatus(user.getId());
        if (userlock != null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("tài khoản của bạn đang bị khóa cho đến :  " + userlock.getDayLockEnd()
                            + " \nVì lí do : " + userlock.getReasonLock()
                    );
        }
        userLoginHistoryService.saveNewLoginSession(user);
        return ResponseEntity.ok(new JwtResponse(jwtToken, username, rolename));
    }

    public String getMaxRoleName(String username) {
        String rolename = "ROLE_VISITOR";
        Set<String> role_names = new HashSet<>();
        User user = userService.findByUserName(username);
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            role_names.add(role.getName());
        }
        if (role_names.contains("ROLE_USER")) {
            rolename = "ROLE_USER";
        }
        if (role_names.contains("ROLE_MEMBER")) {
            rolename = "ROLE_MEMBER";
        }
        if (role_names.contains("ROLE_ADMIN")) {
            rolename = "ROLE_ADMIN";
        }
        return rolename;
    }

    @PostMapping("/login-history")
    public ResponseEntity<?> getAllHistoryLogin(@RequestBody String username) {
        User user = userService.findByUserName("admin");
        List<String> userLoginHistoryList = userLoginHistoryService.getAllLoginTime(user);
        return ResponseEntity.ok(userLoginHistoryList);
    }

    @Autowired
    private HistoryAuctionProductService historyAuctionProductService;

    @Autowired
    private UserProfileRepository userProfileRepository;


    @GetMapping("getid/{username}")
    public ResponseEntity<?> getIdByUserName(@PathVariable("username") String username){
        User user =userService.findByUserName(username);
        UserProfile userProfile = userProfileService.findById(user.getId());
        return new ResponseEntity<>(userProfile, HttpStatus.OK);
    }
    //Get info to idUserProfile
    @GetMapping("/user/{id}")
    public ResponseEntity<?> getAllInfoUser(@PathVariable("id") Long id) {
        UserProfile userProfiles = userProfileService.findAllProfileUser(id);
        return new ResponseEntity<>(userProfiles, HttpStatus.OK);
    }

    //Update info
    @PutMapping("/user/update/{id}")
    public ResponseEntity<UserProfile> editUserProfile(@RequestBody UserProfile userProfile, @PathVariable("id") long id) {
        UserProfile userProfiles = userProfileService.findById(id);
        if (userProfiles == null) {
            System.out.println("User with id " + id + " not found");
            return new ResponseEntity<UserProfile>(HttpStatus.NOT_FOUND);
        }
        userProfiles.setFullName(userProfile.getFullName());
        userProfiles.setAddress(userProfile.getAddress());
        userProfiles.setDayOfBirth(userProfile.getDayOfBirth());
        userProfiles.setIdentityNumber(userProfile.getIdentityNumber());
        userProfiles.setPhone(userProfile.getPhone());
        userProfiles.setEmail(userProfile.getEmail());
        userProfiles.setId(userProfile.getId());

        userProfileService.editUserProfile(userProfiles);
        return new ResponseEntity<UserProfile>(userProfiles, HttpStatus.OK);

    }

    //history register product


//    @GetMapping("/history-register")
//    public ResponseEntity<?> getAllHistoryRegisterProducts(@PathVariable Long id,@PathVariable Pageable pageable) {
//        Page<HistoryRegisterProductDTO> historyRegisterProductDTOS = historyRegisterProductService.getAllHistoryRegisterProduct(pageable,id);
//        return new ResponseEntity<>(historyRegisterProductDTOS, HttpStatus.OK);
//    }

    //save
    @PutMapping("user/{userID}")
    public ResponseEntity<?> getAllHistoryRegisterProducts(@PathVariable("userID") Long userID,
                                                           @RequestBody UseProfileDTO useProfileDTO
                                                           ) {
        UserProfile userProfile = new UserProfile();
        userProfile.setId(userID);
        userProfile.setFullName(useProfileDTO.getFullName());
        userProfile.setAddress(useProfileDTO.getAddress());
        userProfile.setDayOfBirth(useProfileDTO.getDayOfBirth());
        userProfile.setIdentityNumber(useProfileDTO.getIdentityNumber());
        userProfile.setPhone(useProfileDTO.getPhone());
        userProfile.setEmail(useProfileDTO.getEmail());
        try{
            userProfileRepository.save(userProfile); // ssave va update nhu nhau
            return new ResponseEntity<>(null, HttpStatus.OK);
        }catch(Exception e){
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }



    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(@RequestBody() ChangePasswordDTO changePasswordDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(changePasswordDTO.getUsername(), changePasswordDTO.getOldPassword())
            );
            userService.changePassword(changePasswordDTO.getUsername(), changePasswordDTO.getNewPassword());
            return ResponseEntity.ok("");
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Thông tin tài khoản không chính xác");
        }
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody() ResetPasswordDTO resetPasswordDTO) {

        try {
            User user = userService.findByUserName(resetPasswordDTO.getUserName());
            UserProfile userProfile = userProfileService.getUserProfileByEmail(resetPasswordDTO.getEmail());
            if (user.getUserProfile().equals(userProfile)) {
                RandomString randomString = new RandomString();
                String newPassword = randomString.getAlphaNumericString(20);
                System.out.println(newPassword);
                user.setPassword(newPassword);
                userService.changePassword(user.getUserName(),newPassword);
                SendGmailService sendGmailService = new SendGmailService();
                sendGmailService.setReceiverMail(resetPasswordDTO.getEmail());
                sendGmailService.setTitle("Mật khẩu mới");
                sendGmailService.setContent("mật khẩu mới của bạn là " + newPassword);
                sendGmailService.sendMail();
                return ResponseEntity.ok("");
            }
            else {
                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body("Thông tin tài khoản không chính xác");
            }
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Thông tin tài khoản không chính xác");
        }
    }

    @PostMapping("save-product")
    public ResponseEntity saveProduct(@RequestBody ProductInforDTO productInforDTO) {
        Product product = productInforDTO.toProduct();
        User user = userService.findByUserName(productInforDTO.getUserName());
        ProductCatalogue productCatalogue = catalogueService.findByName(productInforDTO.getCatalogue());
        product.setProductCatalogue(productCatalogue);
        product.setUser(user);
        System.out.println("buoc 3");
        return ResponseEntity.ok(productService.save(product));
    }
    @PostMapping("get-infor-user")
    public ResponseEntity getInfor(@RequestBody String userName) {
        User user = userService.findByUserName(userName);
        UserProfile userProfile = user.getUserProfile();
        Object object = new Object() {
            private Long idUser = userProfile.getId();
            private String fullName = userProfile.getFullName();
            private String email = userProfile.getEmail();
            private String userName = user.getUserName();
            private String phone = userProfile.getPhone();
            public String getFullName() {
                return fullName;
            }
            public void setFullName(String fullName) {
                this.fullName = fullName;
            }
            public String getEmail() {
                return email;
            }
            public void setEmail(String email) {
                this.email = email;
            }
            public Long getIdUser() {
                return idUser;
            }
            public void setIdUser(Long idUser) {
                this.idUser = idUser;
            }
            public String getUserName() {
                return userName;
            }
            public void setUserName(String userName) {
                this.userName = userName;
            }
            public String getPhone() {
                return phone;
            }
            public void setPhone(String phone) {
                this.phone = phone;
            }
        };
        return ResponseEntity.ok(object);
    }
    @GetMapping("get-infor-product")
    public ResponseEntity getInforProduct(@RequestParam("id") Long id) {
        System.out.println(id);
        Product product = productService.findById(id);
        return ResponseEntity.ok(product.toProductInforDTO());
    }
}

