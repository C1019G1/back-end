package com.codegym.dao.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column()
    private Long id;

    @Column
    private String fullName;

    @Column
    private String email;

    @Column
    private String phone;

    @Column
    private int identityNumber;

    @Column
    private String address;

    @Column
    private int contributePoint;

    @Temporal(TemporalType.DATE)
    @Column()
    private Date dayOfBirth;


    @ManyToOne
    @JoinColumn(name = "user_rank_id")
    private UserRank rank;

    public UserProfile() {
        // Contructor
    }

    public UserProfile(String fullName, String email, String phone, Long identityNumber, String address, int contributePoint, Date dayOfBirth, UserRank rank) {
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public int getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(int identityNumber) {
        this.identityNumber = identityNumber;
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

    public UserRank getRank() {
        return rank;
    }

    public void setRank(UserRank rank) {
        this.rank = rank;
    }
}
