package com.codegym.dao.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "registered_product_id", nullable = false)
    private RegisteredProduct registeredProduct;
    @Column
    private Date betTime;
    @Column
    private Long betPrice;

    public Auction() {
    }

    public Auction(User user, RegisteredProduct registeredProduct, Date betTime, Long betPrice) {
        this.user = user;
        this.registeredProduct = registeredProduct;
        this.betTime = betTime;
        this.betPrice = betPrice;
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

    public RegisteredProduct getRegisteredProduct() {
        return registeredProduct;
    }

    public void setRegisteredProduct(RegisteredProduct registeredProduct) {
        this.registeredProduct = registeredProduct;
    }

    public Date getBetTime() {
        return betTime;
    }

    public void setBetTime(Date betTime) {
        this.betTime = betTime;
    }

    public Long getBetPrice() {
        return betPrice;
    }

    public void setBetPrice(Long betPrice) {
        this.betPrice = betPrice;
    }
}
