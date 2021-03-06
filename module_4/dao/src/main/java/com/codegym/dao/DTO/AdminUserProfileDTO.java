package com.codegym.dao.DTO;

import java.util.Date;
import java.util.Optional;

public class AdminUserProfileDTO {
    private Long id;
    private String fullName;
    private String address;
    private String rank;
    private String email;
    private String phoneNumber;
    private Optional<Date> lastLogin;
    private int contributePoint;
    private boolean status;

    public AdminUserProfileDTO() {
        // Contructor
    }


    public Optional<Date> getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Optional<Date> lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


    public int getContributePoint() {
        return contributePoint;
    }

    public void setContributePoint(int contributePoint) {
        this.contributePoint = contributePoint;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
