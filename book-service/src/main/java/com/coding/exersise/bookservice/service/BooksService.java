package com.coding.exersise.bookservice.service;

import com.coding.exersise.bookservice.model.Book;
import com.coding.exersise.bookservice.repository.BooksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
@Validated
public class BooksService {

    private final BooksRepository booksRepository;


    public List<Book> getAllBooks() {
        return booksRepository.findAll();
    }

    public ResponseEntity<Book> getBook(@Valid String isbn) {

        Optional<Book> bookData = booksRepository.findById(isbn);

        if (bookData.isPresent()) {
            return new ResponseEntity<>(bookData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public Book createBook(@Valid Book  book) {
        return booksRepository.save(book);
    }
}
