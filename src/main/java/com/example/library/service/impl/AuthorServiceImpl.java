package com.example.library.service.impl;

import com.example.library.dto.AuthorDTO;
import com.example.library.entity.Author;
import com.example.library.exception.ResourceNotFoundException;
import com.example.library.mapper.AuthorMapper;
import com.example.library.repository.AuthorRepository;
import com.example.library.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Override
    @Transactional(readOnly = true)
    public List<AuthorDTO> getAll() {
        return authorRepository.findAll().stream()
                .map(AuthorMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public AuthorDTO getById(Long id) {
        return authorRepository.findById(id)
                .map(AuthorMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id " + id));
    }

    @Override
    public AuthorDTO create(AuthorDTO dto) {
        Author entity = AuthorMapper.toEntity(dto);
        Author saved = authorRepository.save(entity);
        return AuthorMapper.toDto(saved);
    }

    @Override
    public AuthorDTO update(Long id, AuthorDTO dto) {
        Author existing = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id " + id));
        existing.setName(dto.getName());
        Author saved = authorRepository.save(existing);
        return AuthorMapper.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Author not found with id " + id);
        }
        authorRepository.deleteById(id);
    }
} 