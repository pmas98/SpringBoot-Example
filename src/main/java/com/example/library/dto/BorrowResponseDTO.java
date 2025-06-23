package com.example.library.dto;

import lombok.Builder;
import lombok.Value;

import java.time.LocalDate;

@Value
@Builder
public class BorrowResponseDTO {
    Long loanId;
    Long bookId;
    Long borrowerId;
    LocalDate borrowDate;
    LocalDate returnDate;
} 