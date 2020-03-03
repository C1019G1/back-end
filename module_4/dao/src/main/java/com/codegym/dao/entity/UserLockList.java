package com.codegym.dao.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class UserLockList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column
    private String reasonLock;

    @Column
    private Date dayLockStart;

    @Column
    private Date dayLockEnd;

}
