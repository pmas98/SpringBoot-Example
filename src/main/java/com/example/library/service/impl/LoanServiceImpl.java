package com.example.library.service.impl;

import com.example.library.dto.BorrowRequestDTO;
import com.example.library.dto.BorrowResponseDTO;
import com.example.library.entity.Book;
import com.example.library.entity.Borrower;
import com.example.library.entity.Loan;
import com.example.library.exception.BookUnavailableException;
import com.example.library.exception.ResourceNotFoundException;
import com.example.library.repository.BookRepository;
import com.example.library.repository.BorrowerRepository;
import com.example.library.repository.LoanRepository;
import com.example.library.service.LoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Transactional
public class LoanServiceImpl implements LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final BorrowerRepository borrowerRepository;

    @Override
    public BorrowResponseDTO borrowBook(BorrowRequestDTO request) {
        Book book = bookRepository.findById(request.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id " + request.getBookId()));
        if (book.getAvailableCopies() <= 0) {
            throw new BookUnavailableException("No copies available for book id " + book.getId());
        }

        Borrower borrower = borrowerRepository.findById(request.getBorrowerId())
                .orElseThrow(() -> new ResourceNotFoundException("Borrower not found with id " + request.getBorrowerId()));

        book.setAvailableCopies(book.getAvailableCopies() - 1);
        Loan loan = Loan.builder()
                .book(book)
                .borrower(borrower)
                .borrowDate(LocalDate.now())
                .build();
        Loan saved = loanRepository.save(loan);
        // book is updated due to cascade/calls; but we may save book separately
        bookRepository.save(book);

        return toResponseDTO(saved);
    }

    @Override
    public BorrowResponseDTO returnBook(Long loanId) {
        Loan loan = loanRepository.findById(loanId)
                .orElseThrow(() -> new ResourceNotFoundException("Loan not found with id " + loanId));
        if (loan.getReturnDate() != null) {
            throw new IllegalStateException("Book already returned for loan id " + loanId);
        }

        loan.setReturnDate(LocalDate.now());
        Loan saved = loanRepository.save(loan);

        Book book = saved.getBook();
        book.setAvailableCopies(book.getAvailableCopies() + 1);
        bookRepository.save(book);

        return toResponseDTO(saved);
    }

    private BorrowResponseDTO toResponseDTO(Loan loan) {
        return BorrowResponseDTO.builder()
                .loanId(loan.getId())
                .bookId(loan.getBook().getId())
                .borrowerId(loan.getBorrower().getId())
                .borrowDate(loan.getBorrowDate())
                .returnDate(loan.getReturnDate())
                .build();
    }
} 