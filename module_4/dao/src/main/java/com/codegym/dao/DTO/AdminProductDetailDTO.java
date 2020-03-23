package com.codegym.dao.DTO;

import java.util.Date;

public class AdminProductDetailDTO {
    private String nameProduct;
    private Long idUser;
    private String username;
    private String email;
    private String catalogue;
    private Long startPrice;
    private Long minBet;
    private String img;
    private Date startDay;
    private Date endDay;
    private String productInfo;

    public AdminProductDetailDTO() {
    }

    public AdminProductDetailDTO(String nameProduct, Long idUser, String username, String email, String catalogue, Long startPrice, Long minBet, String img, Date startDay, Date endDay, String productInfo) {
        this.nameProduct = nameProduct;
        this.idUser = idUser;
        this.username = username;
        this.email = email;
        this.catalogue = catalogue;
        this.startPrice = startPrice;
        this.minBet = minBet;
        this.img = img;
        this.startDay = startDay;
        this.endDay = endDay;
        this.productInfo = productInfo;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCatalogue() {
        return catalogue;
    }

    public void setCatalogue(String catalogue) {
        this.catalogue = catalogue;
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

    public String getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }
}
