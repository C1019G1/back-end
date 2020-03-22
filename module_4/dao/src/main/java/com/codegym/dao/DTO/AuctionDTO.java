package com.codegym.dao.DTO;

import java.util.Date;

public class AuctionDTO {
    private Long betPrice;
    private Date betTime;
    private String userName;

    public AuctionDTO(Long betPrice, Date autionDate, String userName) {
        this.betPrice = betPrice;
        this.betTime = autionDate;
        this.userName = userName;
    }

    public AuctionDTO() {
    }

    public Long getBetPrice() {
        return betPrice;
    }

    public void setBetPrice(Long betPrice) {
        this.betPrice = betPrice;
    }

    public Date getBetTime() {
        return betTime;
    }

    public void setBetTime(Date betTime) {
        this.betTime = betTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
