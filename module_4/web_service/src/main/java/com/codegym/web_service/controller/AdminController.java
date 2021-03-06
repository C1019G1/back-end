package com.codegym.web_service.controller;

import com.codegym.dao.DTO.*;
import com.codegym.dao.entity.*;
import com.codegym.service.*;
import com.codegym.service.ipml.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@EnableScheduling
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("admin")
public class AdminController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    CatalogueService catalogueService;
    @Autowired
    ProductService productService;
    @Autowired
    UserRankService userRankService;
    @Autowired
    UserProfileService userProfileService;
    @Autowired
    UserLockListService userLockListService;
    @Autowired
    AdminProductManagerService adminProductManagerService;
    @Autowired
    ImageService imageService;

    @GetMapping("user-list")
    public ResponseEntity<Page<AdminUserProfileDTO>> getUserList(@RequestParam(name = "page") int page,
                                                                 @RequestParam(name = "size") int size,
                                                                 @RequestParam(name = "name", defaultValue = "") String name,
                                                                 @RequestParam(name = "rank", defaultValue = "") String rank
    ) {
        Page<AdminUserProfileDTO> adminUserProfileDTOS;
        adminUserProfileDTOS = userService.getUsersProfileByNameByRank(PageRequest.of(page, size), name, rank);
        return new ResponseEntity(adminUserProfileDTOS, HttpStatus.OK);
    }

    @GetMapping("find")
    public ResponseEntity<AdminUserProfileDTO> searchUser(@RequestParam(name = "id", defaultValue = "") Long id,
                                                          @RequestParam(name = "email", defaultValue = "") String email) {
        AdminUserProfileDTO userProfileDTO = new AdminUserProfileDTO();
        if (id != null && !email.equals("")) {
            userProfileDTO = userService.getUserProfileDTOByIdAndEmail(id, email);
        } else {
            if (id != null) {
                userProfileDTO = userService.getUserProfileDTOById(id);
            } else {
                if (!email.equals("")) {
                    userProfileDTO = userService.getUserProfileDTOByEmail(email);
                }
            }
        }
        if (userProfileDTO != null) {
            return new ResponseEntity(userProfileDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("rank-list")
    public ResponseEntity<Iterable<UserRank>> getRankList() {
        return new ResponseEntity(userRankService.getAllRank(), HttpStatus.OK);
    }

    @PostMapping("user-register")
    public ResponseEntity userRegisterByAdmin(@RequestBody UserRegisterDTO userRegisterDTO) {
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

    @PostMapping("user-lock")
    public ResponseEntity userlockByAdmin(@RequestBody AdminUserLockListDTO userLockListDTO) {
        if (this.userLockListService.save(userLockListDTO)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Biểu mẫu không có giá trị");
        }
    }

    @PostMapping("save-product")
    public ResponseEntity saveProduct(@RequestBody ProductInforDTO productInforDTO) {
        Set<Image> images = productInforDTO.getImages();
        if ( images == null) {
            images = new HashSet<>();
        }
        for (String url : productInforDTO.getImgUrlList()) {
            Image image = new Image();
            image.setUrl(url);
            images.add(imageService.save(image));
        }
        Product product = productInforDTO.toProduct();
        product.setImages(images);
        User user = userService.findByUserName(productInforDTO.getUserName());
        ProductCatalogue productCatalogue = catalogueService.findByName(productInforDTO.getCatalogue());
        product.setUser(user);
        product.setProductCatalogue(productCatalogue);
        return ResponseEntity.ok(productService.save(product));
    }

    @PostMapping("get-infor-admin")
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
        Product product = productService.findById(id);
        return ResponseEntity.ok(product.toProductInforDTO());
    }

    @GetMapping("/prod-manager")
    public ResponseEntity getAllProductManager(@RequestParam("page") int page,
                                                  @RequestParam("size") int size) {
        Page<AdminProductManagerDTO> adminProductManagerDTOS = adminProductManagerService.getAllProduct(PageRequest.of(page, size));
        return new ResponseEntity<>(adminProductManagerDTOS.getContent(), HttpStatus.OK);
    }

    @GetMapping(value = "/prod-manager/search", params = {"page", "size", "nameProduct", "catalogue", "userName", "startPrice1", "startPrice2", "status"})
    public ResponseEntity getAllProductByNameProductAndCatalogueAndUserNameAndStartPriceAndStatus(@RequestParam("page") int page,
                                                                                                     @RequestParam("size") int size,
                                                                                                     @RequestParam("nameProduct") String nameProduct,
                                                                                                     @RequestParam("catalogue") String catalogueName,
                                                                                                     @RequestParam("userName") String userName,
                                                                                                     @RequestParam("startPrice1") Long startPrice1,
                                                                                                     @RequestParam("startPrice2") Long startPrice2,
                                                                                                     @RequestParam("status") Boolean pendingStatus) {
        Page<AdminProductManagerDTO> adminProductManagerDTOS = adminProductManagerService.getAllProductByNameProductAndCatalogueAndUserNameAndStartPriceAndStatus(nameProduct, catalogueName, userName, startPrice1, startPrice2, pendingStatus, PageRequest.of(page, size));
        return new ResponseEntity<>(adminProductManagerDTOS.getContent(), HttpStatus.OK);
    }

    @GetMapping("/prod-detail/{id}")
    public ResponseEntity getIdProductDetail(@PathVariable Long id) {
        AdminProductDetailDTO adminProductDetailDTO = adminProductManagerService.getByIdProduct(id);
        return new ResponseEntity<>(adminProductDetailDTO, HttpStatus.OK);
    }
}
