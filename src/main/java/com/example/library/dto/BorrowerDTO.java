package com.example.library.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BorrowerDTO {
    Long id;
    String name;
    String email;
} 