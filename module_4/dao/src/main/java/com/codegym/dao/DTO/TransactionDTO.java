package com.codegym.dao.DTO;

import com.codegym.dao.entity.ProductCatalogue;

import java.util.Date;
import java.util.List;

public class TransactionDTO {
    private Long id;
    private String productName;
    private Date period;
    private int fee;
    private Long price;
    private List<String> imageURLs;
    private Long productId;
    private ProductCatalogue productCatalogue;
    private String productInfo;
    private String contractPhoneNumber;
    private String contractAddress;
    private String warranty;
    public TransactionDTO() {
        // constructor
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Date getPeriod() {
        return period;
    }

    public void setPeriod(Date period) {
        this.period = period;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public List<String> getImageURLs() {
        return imageURLs;
    }

    public void setImageURLs(List<String> imageURLs) {
        this.imageURLs = imageURLs;
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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public ProductCatalogue getProductCatalogue() {
        return productCatalogue;
    }

    public void setProductCatalogue(ProductCatalogue productCatalogue) {
        this.productCatalogue = productCatalogue;
    }
}
