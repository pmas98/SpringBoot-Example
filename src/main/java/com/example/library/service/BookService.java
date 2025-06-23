package com.example.library.service;

import com.example.library.dto.BookDTO;

import java.util.List;

public interface BookService {
    List<BookDTO> getAll();
    BookDTO getById(Long id);
    BookDTO create(BookDTO dto);
    BookDTO update(Long id, BookDTO dto);
    void delete(Long id);
} 