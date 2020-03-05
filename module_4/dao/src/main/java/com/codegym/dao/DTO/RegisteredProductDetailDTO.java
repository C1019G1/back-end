package com.codegym.dao.DTO;

import java.util.Date;

public class RegisteredProductDetailDTO {
    private Long id;
    private String name_product;
    private Long current_price;
    private Date end_day;
    private String img;
    private String catalogue;
    private Long min_bet;
    private Long product_id;
    private String contract_address;
    private String contract_phone_number;
    private Date start_day;
    private Long start_price;
    private String product_info;

    public RegisteredProductDetailDTO() {
    }

    public RegisteredProductDetailDTO(Long id, String name_product, Long current_price, Date end_day, String img, String catalogue, Long min_bet, Long product_id, String contract_address, String contract_phone_number, Date start_day, Long start_price, String product_info) {
        this.id = id;
        this.name_product = name_product;
        this.current_price = current_price;
        this.end_day = end_day;
        this.img = img;
        this.catalogue = catalogue;
        this.min_bet = min_bet;
        this.product_id = product_id;
        this.contract_address = contract_address;
        this.contract_phone_number = contract_phone_number;
        this.start_day = start_day;
        this.start_price = start_price;
        this.product_info = product_info;

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

    public Long getMin_bet() {
        return min_bet;
    }

    public void setMin_bet(Long min_bet) {
        this.min_bet = min_bet;
    }

    public Long getProduct_id() {
        return product_id;
    }

    public void setProduct_id(Long product_id) {
        this.product_id = product_id;
    }

    public String getContract_address() {
        return contract_address;
    }

    public void setContract_address(String contract_address) {
        this.contract_address = contract_address;
    }

    public String getContract_phone_number() {
        return contract_phone_number;
    }

    public void setContract_phone_number(String contract_phone_number) {
        this.contract_phone_number = contract_phone_number;
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
}
