package com.example.library.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Value;

@Value
public class BorrowRequestDTO {
    @NotNull
    Long bookId;

    @NotNull
    Long borrowerId;
} 