package com.codegym.dao.entity;

import javax.persistence.*;

@Entity
public class RegisteredProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column
    private Long view;

    @Column
    private Long currentPrice;

    @Column
    private boolean status;

    public RegisteredProduct() {
    }

    public RegisteredProduct(Product product, Long view, Long currentPrice, boolean status) {
        this.product = product;
        this.view = view;
        this.currentPrice = currentPrice;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getView() {
        return view;
    }

    public void setView(Long view) {
        this.view = view;
    }

    public Long getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Long currentPrice) {
        this.currentPrice = currentPrice;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
