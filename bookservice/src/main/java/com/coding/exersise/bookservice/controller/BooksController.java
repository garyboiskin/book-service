package com.coding.exersise.bookservice.controller;

import com.coding.exersise.bookservice.model.Book;
import com.coding.exersise.bookservice.service.BooksService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1")
@RequiredArgsConstructor
@Validated
public class BooksController {

    private final BooksService bookService;

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        try {
            List<Book> books = bookService.getAllBooks();

            if (books.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/books", params = {"isbn"})
    public ResponseEntity<Book> getBook(@RequestParam(required = true) @Valid  String isbn) {

        return bookService.getBook(isbn);
    }

    @PostMapping("/books")
    public ResponseEntity<Book> createBook(
            @Valid  @RequestBody(required = true)  Book book) {
        try {

            if ((getBook(book.getIsbn())).getBody() != null) {
                StringBuilder stringBuilder = new StringBuilder();
                String error = stringBuilder.append("Book with ISBN: ").
                        append(book.getIsbn()).append(" already exists").toString();
                return new ResponseEntity(Collections.singletonMap("error", error), HttpStatus.ALREADY_REPORTED);
            }
            List<Book> bookList = getBooks().getBody();

            if (bookList.stream()
                    .filter(book::equals)
                    .findAny()
                    .orElse(null) != null) {
                StringBuilder stringBuilder = new StringBuilder();
                String error = stringBuilder.append("Book with Title: ").append(book.getTitle())
                        .append(", Author: ").append(book.getAuthor())
                        .append(" and  Date published: ").append(book.getDatePublished())
                        .append(" already exists").toString();
                return new ResponseEntity(Collections.singletonMap("error", error), HttpStatus.ALREADY_REPORTED);
            }

            System.out.println(book);
            Book newBook = bookService.createBook(book);
            return new ResponseEntity<>(newBook, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

/*    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }*/

}
