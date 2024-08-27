package com.rochman.book_rent.controller;

import com.rochman.book_rent.constant.PathUrl;
import com.rochman.book_rent.dto.request.CreateBookRequest;
import com.rochman.book_rent.dto.request.UpdateBookRequest;
import com.rochman.book_rent.dto.response.BookResponse;
import com.rochman.book_rent.service.BookService;
import com.rochman.book_rent.dto.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PathUrl.BOOK)
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<CommonResponse<BookResponse>> createBook(@RequestBody CreateBookRequest createBookRequest) {
        BookResponse bookResponse = bookService.create(createBookRequest);
        CommonResponse<BookResponse> response = CommonResponse.<BookResponse>builder()
                .message("Book created successfully")
                .statusCode(HttpStatus.CREATED.value())
                .data(bookResponse)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<BookResponse>> updateBook(@RequestBody UpdateBookRequest updateBookRequest) {
        BookResponse bookResponse = bookService.update(updateBookRequest);
        CommonResponse<BookResponse> response = CommonResponse.<BookResponse>builder()
                .message("Book updated successfully")
                .statusCode(HttpStatus.OK.value())
                .data(bookResponse)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<BookResponse>> getBookById(@PathVariable String id) {
        BookResponse bookResponse = bookService.getById(id);
        CommonResponse<BookResponse> response = CommonResponse.<BookResponse>builder()
                .message("Book fetch successfully")
                .statusCode(HttpStatus.OK.value())
                .data(bookResponse)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<BookResponse>>> getAllBooks() {
        List<BookResponse> books = bookService.getAll();
        CommonResponse<List<BookResponse>> response = CommonResponse.<List<BookResponse>>builder()
                .message("Books get all successfully")
                .statusCode(HttpStatus.OK.value())
                .data(books)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<Void>> deleteBook(@PathVariable String id) {
        bookService.delete(id);
        CommonResponse<Void> response = CommonResponse.<Void>builder()
                .message("Book deleted successfully")
                .statusCode(HttpStatus.NO_CONTENT.value())
                .build();
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
