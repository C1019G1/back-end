package com.codegym.dao.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class UserLockList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String reasonLock;

    @Column
    private Date dayLockStart;

    @Column
    private Date dayLockEnd;

    public UserLockList() {
    }

    public UserLockList(User user, String reasonLock, Date dayLockStart, Date dayLockEnd) {
        this.user = user;
        this.reasonLock = reasonLock;
        this.dayLockStart = dayLockStart;
        this.dayLockEnd = dayLockEnd;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getReasonLock() {
        return reasonLock;
    }

    public void setReasonLock(String reasonLock) {
        this.reasonLock = reasonLock;
    }

    public Date getDayLockStart() {
        return dayLockStart;
    }

    public void setDayLockStart(Date dayLockStart) {
        this.dayLockStart = dayLockStart;
    }

    public Date getDayLockEnd() {
        return dayLockEnd;
    }

    public void setDayLockEnd(Date dayLockEnd) {
        this.dayLockEnd = dayLockEnd;
    }
}
