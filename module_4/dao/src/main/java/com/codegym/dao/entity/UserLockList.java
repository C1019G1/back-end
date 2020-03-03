package com.codegym.dao.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_lock_list")
public class UserLockList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_status_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "reason_lock")
    private String reasonLock;

    @Column(name = "user_lock_start")
    private Date dayLockStart;

    @Column(name = "user_lock_end")
    private Date dayLockEnd;



}
