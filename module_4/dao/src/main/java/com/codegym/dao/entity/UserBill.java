package com.codegym.dao.entity;


import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class UserBill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    @JoinTable(
            name = "user_transaction_bill",
            joinColumns = @JoinColumn(name = "user_bill_id"),
            inverseJoinColumns = @JoinColumn(name = "user_transaction_id")
    )
    private Set<UserTransaction> userTransactions;
    @Column
    private Date billDay;
    @Column
    private String receiverName;
    @Column
    private String receiverAddress;
    @Column
    private String receiverPhone;
    @Column
    private String receiverEmail;

    public UserBill() {
        // constructor
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<UserTransaction> getUserTransactions() {
        return userTransactions;
    }

    public void setUserTransactions(Set<UserTransaction> userTransactions) {
        this.userTransactions = userTransactions;
    }

    public Date getBillDay() {
        return billDay;
    }

    public void setBillDay(Date billDay) {
        this.billDay = billDay;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }
}
