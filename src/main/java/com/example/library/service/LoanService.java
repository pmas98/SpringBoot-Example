package com.example.library.service;

import com.example.library.dto.BorrowRequestDTO;
import com.example.library.dto.BorrowResponseDTO;

public interface LoanService {
    BorrowResponseDTO borrowBook(BorrowRequestDTO request);
    BorrowResponseDTO returnBook(Long loanId);
} 