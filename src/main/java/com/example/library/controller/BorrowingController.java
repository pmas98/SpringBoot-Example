package com.example.library.controller;

import com.example.library.dto.BorrowRequestDTO;
import com.example.library.dto.BorrowResponseDTO;
import com.example.library.service.LoanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/loans")
@RequiredArgsConstructor
public class BorrowingController {

    private final LoanService loanService;

    @PostMapping
    public ResponseEntity<BorrowResponseDTO> borrowBook(@RequestBody @Valid BorrowRequestDTO request) {
        BorrowResponseDTO response = loanService.borrowBook(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/{loanId}/return")
    public ResponseEntity<BorrowResponseDTO> returnBook(@PathVariable Long loanId) {
        BorrowResponseDTO response = loanService.returnBook(loanId);
        return ResponseEntity.ok(response);
    }
} 