package com.codegym.dao.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "product_catalogue")
public class ProductCatalogue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(targetEntity = Product.class)
    private List<Product> products;
}
