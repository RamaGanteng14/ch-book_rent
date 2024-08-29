package com.rochman.book_rent.service.impl;

import com.rochman.book_rent.dto.request.CreateBookRequest;
import com.rochman.book_rent.dto.request.UpdateBookRequest;
import com.rochman.book_rent.dto.request.UpdateCategoryRequest;
import com.rochman.book_rent.dto.response.BookResponse;
import com.rochman.book_rent.dto.response.CategoryResponse;
import com.rochman.book_rent.entity.Book;
import com.rochman.book_rent.entity.Category;
import com.rochman.book_rent.repository.BookRepository;
import com.rochman.book_rent.service.BookService;
import com.rochman.book_rent.service.CategoryService;
import com.rochman.book_rent.util.Validation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Year;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final CategoryService categoryService;
    private final Validation validation;

    @Override
    public BookResponse create(CreateBookRequest createBookRequest) {
        return save(createBookRequest);
    }

    @Override
    public BookResponse update(UpdateBookRequest updateBookRequest) {
        validation.validate(updateBookRequest);

        Book book = bookRepository.findById(updateBookRequest.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));

        Set<Category> categories = updateBookRequest.getCategoryIds().stream()
                .map(categoryId -> categoryService.findById(categoryId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found")))
                .collect(Collectors.toSet());

        book.setTitle(updateBookRequest.getTitle());
        book.setStock(updateBookRequest.getStock());
        book.setCategories(categories);

        Book updatedBook = bookRepository.saveAndFlush(book);
        return toBookResponse(updatedBook);
    }


    @Override
    public BookResponse getById(String id) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "book not found"));
        System.out.println("DD BUKU"+book);
        return toBookResponse(book);
    }

    @Override
    public List<BookResponse> getByCategoryId() {
        return List.of();
    }

    @Override
    public List<BookResponse> getAll() {
        return bookRepository.findAll().stream()
                .map(this::toBookResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
        bookRepository.delete(book);
    }


    public Book findByBookId(String id){
        return bookRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "book not found"));
    }

    public BookResponse save(CreateBookRequest createBookRequest){
        validation.validate(createBookRequest);
        Set<Category> categories = createBookRequest.getCategoryIds().stream()
                .map(categoryId -> categoryService.findById(categoryId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found")))
                .collect(Collectors.toSet());

        Book book = new Book();
        book.setTitle(createBookRequest.getTitle());
        book.setStock(createBookRequest.getStock());
        book.setCategories(categories);
        book.setBookCode(generateBookCode());

        Book savedBook = bookRepository.saveAndFlush(book);
        return toBookResponse(savedBook);
    }

    private String generateBookCode() {
        long count = bookRepository.count();
        String currentYear = String.valueOf(Year.now().getValue());
        return "BK-" + currentYear + "-" + (count + 1);
    }

    private BookResponse toBookResponse(Book book) {
       List<CategoryResponse> categoryResponses = book.getCategories().stream()
               .map(category -> CategoryResponse.builder()
                       .id(category.getId())
                       .name(category.getName())
                       .build()).toList();

        return BookResponse.builder()
                .id(book.getId())
                .bookCode(book.getBookCode())
                .title(book.getTitle())
                .stock(book.getStock())
                .categories(categoryResponses)
                .build();
    }

}
