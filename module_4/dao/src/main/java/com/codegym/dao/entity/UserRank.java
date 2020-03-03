package com.codegym.dao.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_rank")
public class UserRank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;


    public UserRank() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
