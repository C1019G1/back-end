package com.codegym.dao.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Role {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( nullable = false)
    private Long id;

    @Column( nullable = false)
    private String name;


    public Role() {
    }

}
