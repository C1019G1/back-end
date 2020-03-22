package com.codegym.dao.DTO;

import com.codegym.dao.entity.Product;

import javax.persistence.Column;
import java.util.Date;

public class ProductInforDTO {
    private Long id;
    private String name;
    private Long startPrice;
    private Long minBet;
    private String productInfo;
    private String warranty;
    private String img;
    private Date startDay;
    private Date endDay;
    private boolean pending_status;
    private boolean approva_status;
    private String userName;
    private String catalogue;
    private String contractPhoneNumber;
    private String contractAddress;
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


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getCatalogue() {
        return catalogue;
    }

    public void setCatalogue(String catalogue) {
        this.catalogue = catalogue;
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

    public boolean isPending_status() {
        return pending_status;
    }

    public void setPending_status(boolean pending_status) {
        this.pending_status = pending_status;
    }

    public boolean isApprova_status() {
        return approva_status;
    }

    public void setApprova_status(boolean approva_status) {
        this.approva_status = approva_status;
    }

    public Product toProduct(){
        Product product =new Product();
        product.setId(this.id);
        product.setName(this.name);
        product.setStartPrice(this.startPrice);
        product.setMinBet(this.minBet);
        product.setProductInfo(this.productInfo);
        product.setWarranty(this.warranty);
        product.setImg(this.img);
        product.setStartDay(this.startDay);
        product.setEndDay(this.endDay);
        product.setContractAddress(this.contractAddress);
        product.setContractPhoneNumber(this.contractPhoneNumber);
        return product;
    }
}
