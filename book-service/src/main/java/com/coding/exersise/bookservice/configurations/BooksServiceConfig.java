package com.coding.exersise.bookservice.configurations;

import com.coding.exersise.bookservice.model.Book;
import com.coding.exersise.bookservice.repository.BooksRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.JANUARY;
import static java.time.Month.MAY;


@Configuration
public class BooksServiceConfig {

    @Bean
    CommandLineRunner commandLineRunner(BooksRepository booksRepository) {

        return args -> {
            Book book1 = new Book("isbn1", "title 1", "author", LocalDate.of(1999, JANUARY, 20), 20);
            Book book2 = new Book("isbn2", "title 2", "author", LocalDate.of(2000, MAY, 20), 30);

            booksRepository.saveAll(List.of(book1, book2));


        };
    }
}
