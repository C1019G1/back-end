package com.codegym.dao.DTO;

import java.util.Date;

public class RegisteredProductDTO {
    private Long id;
    private String name_product;
    private Long current_price;
    private Date end_day;
    private String img;
    private String catalogue;
//    private Long min_bet;
//    private Long product_id;

//    private String contract_address;
//    private String contract_phone_number;

//    private Date start_day;
//    private Long start_price;
//    private String product_info;


    public RegisteredProductDTO() {
    }

    public RegisteredProductDTO(Long id, String name_product, Long current_price, Date end_day, String img, String catalogue) {
        this.id = id;
        this.name_product = name_product;
        this.current_price = current_price;
        this.end_day = end_day;
        this.img = img;
        this.catalogue = catalogue;
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
}
