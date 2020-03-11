package com.codegym.dao.DTO;

import java.util.Date;
import java.util.List;

public class RegisteredProductDetailDTO {
    private Long id;
    private String nameProduct;
    private Long currentPrice;
    private Date endDay;
    private String img;
    private String catalogue;
    private Long minBet;
    private Long productId;
    private String contractAddress;
    private String contractPhoneNumber;
    private Date startDay;
    private Long startPrice;
    private String productInfo;
    private List<String> userList;
    private List<Long> betPriceList;
    private List<Date> betTimeList;

    public RegisteredProductDetailDTO() {
        //constructor
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public Long getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Long currentPrice) {
        this.currentPrice = currentPrice;
    }

    public Date getEndDay() {
        return endDay;
    }

    public void setEndDay(Date endDay) {
        this.endDay = endDay;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getCatalogue() {
        return catalogue;
    }

    public void setCatalogue(String catalogue) {
        this.catalogue = catalogue;
    }

    public Long getMinBet() {
        return minBet;
    }

    public void setMinBet(Long minBet) {
        this.minBet = minBet;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(String contractAddress) {
        this.contractAddress = contractAddress;
    }

    public String getContractPhoneNumber() {
        return contractPhoneNumber;
    }

    public void setContractPhoneNumber(String contractPhoneNumber) {
        this.contractPhoneNumber = contractPhoneNumber;
    }

    public Date getStartDay() {
        return startDay;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }

    public Long getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(Long startPrice) {
        this.startPrice = startPrice;
    }

    public String getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }

    public List<String> getUserList() {
        return userList;
    }

    public void setUserList(List<String> userList) {
        this.userList = userList;
    }

    public List<Long> getBetPriceList() {
        return betPriceList;
    }

    public void setBetPriceList(List<Long> betPriceList) {
        this.betPriceList = betPriceList;
    }

    public List<Date> getBetTimeList() {
        return betTimeList;
    }

    public void setBetTimeList(List<Date> betTimeList) {
        this.betTimeList = betTimeList;
    }
}
