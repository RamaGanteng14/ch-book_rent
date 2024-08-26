package com.rochman.book_rent.repository;

import com.rochman.book_rent.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {
}
