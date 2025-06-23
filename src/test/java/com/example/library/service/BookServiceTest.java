package com.example.library.service;

import com.example.library.dto.BookDTO;
import com.example.library.entity.Author;
import com.example.library.entity.Book;
import com.example.library.repository.AuthorRepository;
import com.example.library.repository.BookRepository;
import com.example.library.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;
    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    private Author author;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        author = Author.builder().id(1L).name("Author").build();
    }

    @Test
    void givenExistingId_whenGetById_thenReturnDto() {
        Book book = Book.builder().id(1L).title("Book").author(author).availableCopies(3).build();
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        BookDTO result = bookService.getById(1L);

        assertThat(result.getId()).isEqualTo(1L);
        assertThat(result.getTitle()).isEqualTo("Book");
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void whenGetAll_thenReturnList() {
        Book book1 = Book.builder().id(1L).title("B1").author(author).availableCopies(2).build();
        Book book2 = Book.builder().id(2L).title("B2").author(author).availableCopies(1).build();
        when(bookRepository.findAll()).thenReturn(List.of(book1, book2));

        List<BookDTO> result = bookService.getAll();

        assertThat(result).hasSize(2);
        verify(bookRepository, times(1)).findAll();
    }
} 