package com.codegym.web_service.controller;

import com.codegym.dao.DTO.UseProfileDTO;
import com.codegym.dao.entity.UserProfile;
import com.codegym.dao.repository.UserProfileRepository;
import com.codegym.service.HistoryAuctionProductService;
import com.codegym.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", exposedHeaders = "Authorization")
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserProfileService userProfileService;

    @Autowired
    private HistoryAuctionProductService historyAuctionProductService;

    @Autowired
    private UserProfileRepository userProfileRepository;


    //Get info to idUserProfile
    @GetMapping("/{id}")
    public ResponseEntity<?> getAllInfoUser(@PathVariable("id") Long id) {
        UserProfile userProfiles = userProfileService.findAllProfileUser(id);
        System.out.println(userProfiles);
        return new ResponseEntity<>(userProfiles, HttpStatus.OK);
    }

    //Update info
    @PutMapping("/update/{id}")
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
    @PutMapping("/{userID}")
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
}

