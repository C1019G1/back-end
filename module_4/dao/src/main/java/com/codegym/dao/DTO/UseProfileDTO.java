package com.codegym.dao.DTO;

import com.codegym.dao.entity.UserRank;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Date;

public class UseProfileDTO {

    @JsonProperty(value = "fullName")
    private String fullName;

    @JsonProperty(value = "email")
    private String email;

    @JsonProperty(value = "phone")
    private String phone;

    @JsonProperty(value = "identityNumber")
    private Long identityNumber;

    @JsonProperty(value = "address")
    private String address;

    @JsonProperty(value = "contributePoint")
    private int contributePoint;

    @JsonProperty(value = "dayOfBirth")
    private Date dayOfBirth;


    @JsonProperty(value = "rank")
    private String rank;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(Long identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getContributePoint() {
        return contributePoint;
    }

    public void setContributePoint(int contributePoint) {
        this.contributePoint = contributePoint;
    }

    public Date getDayOfBirth() {
        return dayOfBirth;
    }

    public void setDayOfBirth(Date dayOfBirth) {
        this.dayOfBirth = dayOfBirth;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public UseProfileDTO(String fullName, String email, String phone, Long identityNumber, String address, int contributePoint, Date dayOfBirth, String rank) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.identityNumber = identityNumber;
        this.address = address;
        this.contributePoint = contributePoint;
        this.dayOfBirth = dayOfBirth;
        this.rank = rank;
    }
}
