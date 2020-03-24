package com.codegym.service;

import com.codegym.dao.entity.ProductCatalogue;

import java.util.List;

public interface CatalogueService {
    public ProductCatalogue findByName(String name);
    public List<ProductCatalogue> getAll();
}
