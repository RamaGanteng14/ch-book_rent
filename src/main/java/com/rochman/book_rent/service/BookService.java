package com.rochman.book_rent.service;

import com.rochman.book_rent.dto.request.BookRequest;
import com.rochman.book_rent.dto.response.BookResponse;

import java.util.List;

public interface BookService {
    BookResponse create(BookRequest bookRequest);
    BookResponse update(BookRequest bookRequest);
    BookResponse getById(String id);
    List<BookResponse> getAll();
    void delete(String id);
}
