package com.codegym.service;


import com.codegym.dao.DTO.HistoryRegisterProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface HistoryRegisterProductService {
   public Page<HistoryRegisterProductDTO> getAllHistoryRegisterProduct(Pageable pageable, Long id);
}
