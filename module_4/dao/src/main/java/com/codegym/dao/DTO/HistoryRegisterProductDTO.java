package com.codegym.dao.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;


public class HistoryRegisterProductDTO {
    @JsonProperty(value = "Id_Product")
    private Long id;
    @JsonProperty(value = "Name_Product")
    private String name_product;
    @JsonProperty(value = "Current_Price")
    private Long current_price;
    @JsonProperty(value = "end_day")
    private Date end_day;
    @JsonProperty(value = "status")
    private boolean status;
    @JsonProperty(value = "start_day")
    private Date start_day;
    @JsonProperty(value = "start_price")
    private Long start_price;
    @JsonProperty(value = "product_info")
    private String product_info;
    @JsonProperty(value = "user_id")
    private Long user_id;

    public HistoryRegisterProductDTO() {
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public Long getCurrent_price() {
        return current_price;
    }

    public void setCurrent_price(Long current_price) {
        this.current_price = current_price;
    }

    public Date getEnd_day() {
        return end_day;
    }

    public void setEnd_day(Date end_day) {
        this.end_day = end_day;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Date getStart_day() {
        return start_day;
    }

    public void setStart_day(Date start_day) {
        this.start_day = start_day;
    }

    public Long getStart_price() {
        return start_price;
    }

    public void setStart_price(Long start_price) {
        this.start_price = start_price;
    }

    public String getProduct_info() {
        return product_info;
    }

    public void setProduct_info(String product_info) {
        this.product_info = product_info;
    }

    public HistoryRegisterProductDTO(Long id, String name_product, Long current_price, Date end_day, boolean status, Date start_day, Long start_price, String product_info, Long user_id) {
        this.id = id;
        this.name_product = name_product;
        this.current_price = current_price;
        this.end_day = end_day;
        this.status = status;
        this.start_day = start_day;
        this.start_price = start_price;
        this.product_info = product_info;
        this.user_id = user_id;
    }
}
