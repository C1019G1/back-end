package com.codegym.service;

import com.codegym.dao.entity.ProductCatalogue;

public interface CatalogueService {
    public ProductCatalogue findByName(String name);
}
