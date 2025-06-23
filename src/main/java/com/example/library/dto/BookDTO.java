package com.example.library.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BookDTO {
    Long id;
    Long authorId;
    String title;
    String authorName;
    Integer availableCopies;
} 