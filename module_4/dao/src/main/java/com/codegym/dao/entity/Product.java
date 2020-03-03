package com.codegym.dao.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table ( name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long id;
    @Column(name = "product_name")
    private String Name;
    @Column(name = "product_start_price")
    private Long startPrice;
    @Column(name = "product_min_bet")
    private Long minBet;
    @Column(name = "product_info")
    private String productInfo;
    @Column(name = "product_contract_phone")
    private String contractPhoneNumber;
    @Column(name = "product_contract_address")
    private String contractAddress;
    @Column(name = "product_warranty")
    private String warranty;
    @Column(name = "product_img")
    private String img;
    @Temporal(TemporalType.DATE)
    @Column(name = "product_start")
    private Date startDay;
    @Temporal(TemporalType.DATE)
    @Column(name = "product_end")
    private Date endDay;
    @Column(name = "product_status")
    private boolean status;

    @ManyToOne
    @JoinColumn(name = "product_catalogue_id")
    private ProductCatalogue productCatalogue;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
