package com.example.readinglist.Repository;

import com.example.readinglist.Entity.Reader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReaderRepository extends JpaRepository<Reader, String> {
}
