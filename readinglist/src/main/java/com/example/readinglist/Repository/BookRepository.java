package com.example.readinglist.Repository;

import com.example.readinglist.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByReader(String reader);
}
