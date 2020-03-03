package com.codegym.dao.entity;

import javax.persistence.*;

@Entity
@Table( name = "product_auction")
public class ProductAuction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_auction_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "registered_product_id", nullable = false)
    private ProductRegistered registered;

    @Column(name = "bet_price")
    private Long betPrice;
}
