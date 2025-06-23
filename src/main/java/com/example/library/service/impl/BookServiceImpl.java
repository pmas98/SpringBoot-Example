package com.example.library.service.impl;

import com.example.library.dto.BookDTO;
import com.example.library.entity.Author;
import com.example.library.entity.Book;
import com.example.library.exception.ResourceNotFoundException;
import com.example.library.mapper.BookMapper;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import com.example.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Override
    @Transactional(readOnly = true)
    public List<BookDTO> getAll() {
        return bookRepository.findAll().stream()
                .map(BookMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public BookDTO getById(Long id) {
        return bookRepository.findById(id)
                .map(BookMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));
    }

    @Override
    public BookDTO create(BookDTO dto) {
        Author author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id " + dto.getAuthorId()));
        Book entity = BookMapper.toEntity(dto, author);
        Book saved = bookRepository.save(entity);
        return BookMapper.toDto(saved);
    }

    @Override
    public BookDTO update(Long id, BookDTO dto) {
        Book existing = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + id));

        existing.setTitle(dto.getTitle());
        existing.setAvailableCopies(dto.getAvailableCopies());
        // For simplicity, we are not updating author here.
        Book saved = bookRepository.save(existing);
        return BookMapper.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found with id " + id);
        }
        bookRepository.deleteById(id);
    }
} 