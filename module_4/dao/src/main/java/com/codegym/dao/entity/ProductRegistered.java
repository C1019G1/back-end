package com.codegym.dao.entity;

import javax.persistence.*;

@Entity
@Table( name = "registered_product")
public class ProductRegistered {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "registered_product_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @Column(name = "product_id")
    private Product product;

    @Column(name = "view")
    private Long view;

    @Column(name = "current_price")
    private Long currentPrice;

    @Column(name = "status")
    private boolean status;
}
