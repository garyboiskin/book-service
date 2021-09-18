package com.coding.exersise.bookservice.controller;

import com.coding.exersise.bookservice.model.Book;
import com.coding.exersise.bookservice.service.BooksService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static java.time.Month.JANUARY;
import static java.time.Month.MAY;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class BooksControllerTest {

    // private final Book book1 = new Book("isbn1", "title 1", "author", LocalDate.of(1999, JANUARY, 20), 20);
    // private final Book book2 = new Book("isbn2", "title 2", "author", LocalDate.of(2000, MAY, 20), 30);

    @Mock
    private BooksService booksService;

    @InjectMocks
    private BooksController booksController;

    @Test
    void getBooks() {
        Book book1 = new Book("isbn1", "title 1", "author", LocalDate.of(1999, JANUARY, 20), 20);
        Book book2 = new Book("isbn2", "title 2", "author", LocalDate.of(2000, MAY, 20), 30);

        when(booksService.getAllBooks()).thenReturn(Arrays.asList(book1, book2));
        booksController = new BooksController(booksService);
        ResponseEntity<List<Book>> listResponseEntity = booksController.getBooks();
        assertNotNull(listResponseEntity);
        List<Book> bookList = listResponseEntity.getBody();
        assertNotNull(bookList);
        assertTrue(bookList.size() == 2);

    }

    @Test
    void getBook() {
    }

    @Test
    void createBook() {
    }
}