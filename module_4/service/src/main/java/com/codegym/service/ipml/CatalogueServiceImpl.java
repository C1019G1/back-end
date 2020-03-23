package com.codegym.service.ipml;

import com.codegym.dao.entity.ProductCatalogue;
import com.codegym.dao.repository.CatalogueRepository;
import com.codegym.service.CatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CatalogueServiceImpl implements CatalogueService {
    @Autowired
    CatalogueRepository catalogueRepository;
    @Override
    public ProductCatalogue findByName(String name) {
        return catalogueRepository.findByName(name);
    }
}
