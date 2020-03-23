package com.codegym.dao.DTO;

import java.util.Date;
import java.util.List;

public class AdminProductManagerDTO {
    private Long productId;
    private String nameProduct;
    private String username;
    private String catalogue;
    private Long startPrice;
    private String productInfo;
    private Boolean status;
    private Boolean BrowseStatus;
    private String img;

    public AdminProductManagerDTO() {
    }

    public AdminProductManagerDTO(Long productId, String nameProduct, String username, String catalogue, Long startPrice, String productInfo, Boolean status, Boolean browseStatus, String img) {
        this.productId = productId;
        this.nameProduct = nameProduct;
        this.username = username;
        this.catalogue = catalogue;
        this.startPrice = startPrice;
        this.productInfo = productInfo;
        this.status = status;
        BrowseStatus = browseStatus;
        this.img = img;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getProductInfo() {
        return productInfo;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getBrowseStatus() {
        return BrowseStatus;
    }

    public void setBrowseStatus(Boolean browseStatus) {
        BrowseStatus = browseStatus;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
