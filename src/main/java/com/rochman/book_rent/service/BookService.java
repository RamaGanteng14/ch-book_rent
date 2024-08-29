package com.rochman.book_rent.service;

import com.rochman.book_rent.dto.request.CreateBookRequest;
import com.rochman.book_rent.dto.request.UpdateBookRequest;
import com.rochman.book_rent.dto.response.BookResponse;

import java.util.List;

public interface BookService {
    BookResponse create(CreateBookRequest createBookRequest);
    BookResponse update(UpdateBookRequest updateBookRequest);
    BookResponse getById(String id);
    List<BookResponse> getByCategoryId();
    List<BookResponse> getAll();
    void delete(String id);
}
