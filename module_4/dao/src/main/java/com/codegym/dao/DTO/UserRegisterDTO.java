package com.codegym.dao.DTO;

import com.codegym.dao.entity.UserProfile;
import com.codegym.dao.entity.UserRank;

import java.util.Date;

public class UserRegisterDTO extends UserProfile {
    private String userName;
    private String password;

    public UserRegisterDTO() {
    }

    public UserRegisterDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public UserRegisterDTO(String fullName, String email, String phone, Long identityNumber, String address, int contributePoint, Date dayOfBirth, UserRank rank, String userName, String password) {
        super(fullName, email, phone, identityNumber, address, contributePoint, dayOfBirth, rank);
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}