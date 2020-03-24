package com.codegym.service;

import com.codegym.dao.entity.Image;
import org.springframework.stereotype.Service;


public interface ImageService {
    Image save(Image image);
}
