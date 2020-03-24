package com.codegym.service.ipml;

import com.codegym.dao.entity.Image;
import com.codegym.dao.repository.ImageRepository;
import com.codegym.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    ImageRepository imageRepository;
    @Override
    public Image save(Image image) {
        return imageRepository.save(image);
    }
}
