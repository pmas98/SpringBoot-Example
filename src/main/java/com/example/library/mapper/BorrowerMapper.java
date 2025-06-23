package com.example.library.mapper;

import com.example.library.dto.BorrowerDTO;
import com.example.library.entity.Borrower;

public final class BorrowerMapper {
    private BorrowerMapper() {}

    public static BorrowerDTO toDto(Borrower borrower) {
        if (borrower == null) return null;
        return BorrowerDTO.builder()
                .id(borrower.getId())
                .name(borrower.getName())
                .email(borrower.getEmail())
                .build();
    }

    public static Borrower toEntity(BorrowerDTO dto) {
        if (dto == null) return null;
        return Borrower.builder()
                .id(dto.getId())
                .name(dto.getName())
                .email(dto.getEmail())
                .build();
    }
} 