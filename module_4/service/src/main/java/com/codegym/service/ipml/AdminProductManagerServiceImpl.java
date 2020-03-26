package com.codegym.service.ipml;
import com.codegym.dao.DTO.AdminProductDetailDTO;
import com.codegym.dao.DTO.AdminProductManagerDTO;
import com.codegym.dao.entity.Image;
import com.codegym.dao.entity.Product;
import com.codegym.dao.repository.ProductRepository;
import com.codegym.service.AdminProductManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class AdminProductManagerServiceImpl implements AdminProductManagerService {
    @Autowired
    ProductRepository productRepository;



    @Override
    public Page<AdminProductManagerDTO> getAllProduct(Pageable pageable) {
        Page<Product> products = productRepository.findAll(pageable);
        Page<AdminProductManagerDTO> productManagerDTOS = products.map(product -> {
            AdminProductManagerDTO adminProductManagerDTO= new AdminProductManagerDTO();
            adminProductManagerDTO.setProductId(product.getId());
            adminProductManagerDTO.setNameProduct(product.getName());
            adminProductManagerDTO.setCatalogue(product.getProductCatalogue().getName());
            adminProductManagerDTO.setUsername(product.getUser().getUserName());
//            adminProductManagerDTO.setImg(product.getImages());
            adminProductManagerDTO.setStartPrice(product.getStartPrice());
            adminProductManagerDTO.setStatus(product.isPendingStatus());
            adminProductManagerDTO.setProductInfo(product.getProductInfo());
            return adminProductManagerDTO;
        });
        return productManagerDTOS;
    }
    private List<String> getImageUrl(Set<Image> images){
        List<String> imageUrl= new ArrayList<>();
        images.forEach(image -> imageUrl.add(image.getUrl()));
        return imageUrl;
    }

    @Override
    public Page<AdminProductManagerDTO> getAllProductByNameProductAndCatalogueAndUserNameAndStartPriceAndStatus(String name, String catalogueName, String userName, Long startPrice1,Long startPrice2, Boolean status, Pageable pageable) {
        Page<Product> products = productRepository.findAllByNameContainingAndProductCatalogueNameContainingAndUserUserNameContainingAndStartPriceBetweenAndPendingStatusTrue(name, catalogueName, userName, startPrice1, startPrice2, pageable);
        Page<AdminProductManagerDTO> productManagerDTOS = products.map(product -> {
            AdminProductManagerDTO adminProductManagerDTO = new AdminProductManagerDTO();
            adminProductManagerDTO.setProductId(product.getId());
            adminProductManagerDTO.setNameProduct(product.getName());
            adminProductManagerDTO.setCatalogue(product.getProductCatalogue().getName());
            adminProductManagerDTO.setUsername(product.getUser().getUserName());
            adminProductManagerDTO.setStartPrice(product.getStartPrice());
            adminProductManagerDTO.setStatus(product.isPendingStatus());
            adminProductManagerDTO.setProductInfo(product.getProductInfo());
            return adminProductManagerDTO;
        });
        return productManagerDTOS;
    }

    @Override
    public AdminProductDetailDTO getByIdProduct(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        AdminProductDetailDTO adminProductDetailDTO= new AdminProductDetailDTO();
        assert product != null;
        adminProductDetailDTO.setNameProduct(product.getName());
        adminProductDetailDTO.setUsername(product.getUser().getUserName());
        adminProductDetailDTO.setIdUser(product.getUser().getId());
        adminProductDetailDTO.setEmail(product.getUser().getUserProfile().getEmail());
        adminProductDetailDTO.setCatalogue(product.getProductCatalogue().getName());
        adminProductDetailDTO.setStartPrice(product.getStartPrice());
        adminProductDetailDTO.setMinBet(product.getMinBet());
        adminProductDetailDTO.setStartDay(product.getStartDay());
        adminProductDetailDTO.setImg(product.getImages().toString());
        adminProductDetailDTO.setEndDay(product.getEndDay());
        adminProductDetailDTO.setProductInfo(product.getProductInfo());
        return adminProductDetailDTO;
    }
}
