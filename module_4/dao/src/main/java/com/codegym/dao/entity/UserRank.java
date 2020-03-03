package com.codegym.dao.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_rank")
public class UserRank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_rank_id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(targetEntity = User.class)
    private List<UserProfile> userProfiles;

    public UserRank() {

    }
}
