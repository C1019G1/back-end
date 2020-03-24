package com.codegym.web_service.controller;

import com.codegym.dao.entity.Image;
import com.codegym.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("img")
public class ImageController {
    @Autowired
    ImageService imageService;
    @GetMapping("/upload")
    public ResponseEntity<?> upload() {

        return ResponseEntity.ok("ok");
    }
}
