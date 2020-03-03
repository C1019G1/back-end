package com.codegym.dao.entity;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_profile")
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_profile_id")
    private Long id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private Long phone;

    @Column(name = "identity_number ")
    private Long identityNumber;

    @Column(name = "address")
    private Long address;

    @Temporal(TemporalType.DATE)
    @Column(name = "dob")
    private Date dayOfBirth;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public UserProfile() {
    }

}
