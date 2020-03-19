package com.codegym.dao.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
public class Product{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private Long startPrice;
    @Column
    private Long minBet;
    @Column
    private String productInfo;
    @Column
    private String contractPhoneNumber;
    @Column
    private String contractAddress;
    @Column
    private String warranty;
    @OneToMany
    @JoinTable(
            name = "product_img",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "image_id")
    )
    private Set<Image> images;
    @Column
    private Date startDay;
    @Column
    private Date endDay;
    @Column
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "product_catalogue_id")
    private ProductCatalogue productCatalogue;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Product() {
        //constructor
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

    public Long getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Long startPrice) {
        this.startPrice = startPrice;
    }

    public Long getMinBet() {
        return minBet;
    }

    public void setMinBet(Long minBet) {
        this.minBet = minBet;
    }

    public String getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }

    public String getContractPhoneNumber() {
        return contractPhoneNumber;
    }

    public void setContractPhoneNumber(String contractPhoneNumber) {
        this.contractPhoneNumber = contractPhoneNumber;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public Date getStartDay() {
        return startDay;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }

    public Date getEndDay() {
        return endDay;
    }

    public void setEndDay(Date endDay) {
        this.endDay = endDay;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ProductCatalogue getProductCatalogue() {
        return productCatalogue;
    }

    public void setProductCatalogue(ProductCatalogue productCatalogue) {
        this.productCatalogue = productCatalogue;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



}
