package com.example.library.service.impl;

import com.example.library.dto.BorrowerDTO;
import com.example.library.entity.Borrower;
import com.example.library.exception.ResourceNotFoundException;
import com.example.library.mapper.BorrowerMapper;
import com.example.library.repository.BorrowerRepository;
import com.example.library.service.BorrowerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BorrowerServiceImpl implements BorrowerService {

    private final BorrowerRepository borrowerRepository;

    @Override
    @Transactional(readOnly = true)
    public List<BorrowerDTO> getAll() {
        return borrowerRepository.findAll().stream()
                .map(BorrowerMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public BorrowerDTO getById(Long id) {
        return borrowerRepository.findById(id)
                .map(BorrowerMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Borrower not found with id " + id));
    }

    @Override
    public BorrowerDTO create(BorrowerDTO dto) {
        Borrower entity = BorrowerMapper.toEntity(dto);
        Borrower saved = borrowerRepository.save(entity);
        return BorrowerMapper.toDto(saved);
    }

    @Override
    public BorrowerDTO update(Long id, BorrowerDTO dto) {
        Borrower existing = borrowerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Borrower not found with id " + id));
        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());
        Borrower saved = borrowerRepository.save(existing);
        return BorrowerMapper.toDto(saved);
    }

    @Override
    public void delete(Long id) {
        if (!borrowerRepository.existsById(id)) {
            throw new ResourceNotFoundException("Borrower not found with id " + id);
        }
        borrowerRepository.deleteById(id);
    }
} 