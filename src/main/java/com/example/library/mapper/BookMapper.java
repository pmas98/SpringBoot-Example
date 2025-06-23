package com.example.library.mapper;

import com.example.library.dto.BookDTO;
import com.example.library.entity.Author;
import com.example.library.entity.Book;

public final class BookMapper {
    private BookMapper() {}

    public static BookDTO toDto(Book book) {
        if (book == null) return null;
        return BookDTO.builder()
                .id(book.getId())
                .authorId(book.getAuthor().getId())
                .title(book.getTitle())
                .authorName(book.getAuthor().getName())
                .availableCopies(book.getAvailableCopies())
                .build();
    }

    public static Book toEntity(BookDTO dto, Author author) {
        if (dto == null) return null;
        return Book.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .author(author)
                .availableCopies(dto.getAvailableCopies())
                .build();
    }
} 