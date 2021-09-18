package com.coding.exersise.bookservice.repository;

import com.coding.exersise.bookservice.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepository extends JpaRepository<Book, String> {
}
