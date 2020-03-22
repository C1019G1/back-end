package com.codegym.dao.entity;

import com.codegym.dao.DTO.ProductInforDTO;

import javax.persistence.*;
import java.util.Date;

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
    @Column
    private String img;
    @Column
    private Date startDay;
    @Column
    private Date endDay;
    @Column()
    private boolean pending_status =true;
    @Column()
    private boolean approva_status = false;

    @ManyToOne
    @JoinColumn(name = "product_catalogue_id")
    private ProductCatalogue productCatalogue;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Product(String name, Long startPrice, Long minBet, String productInfo, String contractPhoneNumber, String contractAddress, String warranty, String img, Date startDay, Date endDay, boolean pending_status, boolean approva_status, ProductCatalogue productCatalogue, User user) {
        this.name = name;
        this.startPrice = startPrice;
        this.minBet = minBet;
        this.productInfo = productInfo;
        this.contractPhoneNumber = contractPhoneNumber;
        this.contractAddress = contractAddress;
        this.warranty = warranty;
        this.img = img;
        this.startDay = startDay;
        this.endDay = endDay;
        this.pending_status = pending_status;
        this.approva_status = approva_status;
        this.productCatalogue = productCatalogue;
        this.user = user;
    }

    public Product() {
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
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
        return this.approva_status;
    }

    public void setStatus(boolean status) {
        this.approva_status = status;
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
    public ProductInforDTO toProductInforDTO(){
        ProductInforDTO productInforDTO  = new ProductInforDTO();
        productInforDTO.setId(this.id);
        productInforDTO.setName(this.name);
        productInforDTO.setStartPrice(this.startPrice);
        productInforDTO.setWarranty(this.warranty);
        productInforDTO.setImg(this.img);
        productInforDTO.setStartDay(this.startDay);
        productInforDTO.setEndDay(this.endDay);
        productInforDTO.setPending_status(this.pending_status);
        productInforDTO.setApprova_status(this.approva_status);
        productInforDTO.setIdUser(this.user.getId());
        productInforDTO.setUserName(this.user.getUserName());
        productInforDTO.setFullName(this.user.getUserProfile().getFullName());
        productInforDTO.setEmail(this.user.getUserProfile().getEmail());
        productInforDTO.setPhone(this.user.getUserProfile().getPhone());
        productInforDTO.setCatalogue(this.productCatalogue.getName());
        productInforDTO.setContractPhoneNumber(this.contractPhoneNumber);
        productInforDTO.setContractAddress(this.contractAddress);
        productInforDTO.setProductInfo(this.productInfo);
        return productInforDTO;
    }
}
