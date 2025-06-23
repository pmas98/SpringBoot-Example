package com.example.library.mapper;

import com.example.library.dto.AuthorDTO;
import com.example.library.entity.Author;

public final class AuthorMapper {
    private AuthorMapper() {}

    public static AuthorDTO toDto(Author author) {
        if (author == null) return null;
        return AuthorDTO.builder()
                .id(author.getId())
                .name(author.getName())
                .build();
    }

    public static Author toEntity(AuthorDTO dto) {
        if (dto == null) return null;
        return Author.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }
} 