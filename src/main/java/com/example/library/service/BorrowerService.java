package com.example.library.service;

import com.example.library.dto.BorrowerDTO;

import java.util.List;

public interface BorrowerService {
    List<BorrowerDTO> getAll();
    BorrowerDTO getById(Long id);
    BorrowerDTO create(BorrowerDTO dto);
    BorrowerDTO update(Long id, BorrowerDTO dto);
    void delete(Long id);
} 