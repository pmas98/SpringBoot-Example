package com.example.library.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class AuthorDTO {
    Long id;
    String name;
} 