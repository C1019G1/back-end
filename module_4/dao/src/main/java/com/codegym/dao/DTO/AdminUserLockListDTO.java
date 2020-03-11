package com.codegym.dao.DTO;

import java.util.Date;
import java.util.List;

public class AdminUserLockListDTO {
    private List<Long> users;
    private String reasonLock;
    private Date dayLockStart;
    private Date dayLockEnd;


    public AdminUserLockListDTO() {
        //constructor
    }


    public List<Long> getUsers() {
        return users;
    }

    public void setUsers(List<Long> users) {
        this.users = users;
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

    public String getReasonLock() {
        return reasonLock;
    }

    public void setReasonLock(String reasonLock) {
        this.reasonLock = reasonLock;
    }

}
