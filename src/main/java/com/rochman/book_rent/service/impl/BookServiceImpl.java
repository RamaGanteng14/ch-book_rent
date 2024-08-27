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

        book.setTitle(updateBookRequest.getTitle());
        book.setStock(updateBookRequest.getStock());

        Set<Category> existingCategories = book.getCategories();
        Set<Category> newCategories = updateBookRequest.getCategoryIds().stream()
                .map(categoryId -> categoryService.findById(categoryId)
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found")))
                .collect(Collectors.toSet());

        // Find categories to add
        Set<Category> categoriesToAdd = new HashSet<>(newCategories);
        categoriesToAdd.removeAll(existingCategories);

        // Find categories to remove
        Set<Category> categoriesToRemove = new HashSet<>(existingCategories);
        categoriesToRemove.removeAll(newCategories);

        // Remove old categories
        book.getCategories().removeAll(categoriesToRemove);

        // Add new categories
        book.getCategories().addAll(categoriesToAdd);

        bookRepository.saveAndFlush(book);
        return mapToResponse(book);
    }



    @Override
    public BookResponse getById(String id) {
        return bookRepository.findById(id)
                .map(this::mapToResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
    }

    @Override
    public List<BookResponse> getAll() {
        return bookRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(String id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found"));
        bookRepository.delete(book);
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
        return mapToResponse(savedBook);
    }

    private String generateBookCode() {
        long count = bookRepository.count();
        String currentYear = String.valueOf(Year.now().getValue());
        return "BK-" + currentYear + "-" + (count + 1);
    }

    private BookResponse mapToResponse(Book book) {
        return BookResponse.builder()
                .id(book.getId())
                .bookCode(book.getBookCode())
                .title(book.getTitle())
                .stock(book.getStock())
                .categories(book.getCategories().stream()
                        .map(category -> CategoryResponse.builder()
                                .id(category.getId())
                                .name(category.getName())
                                .build())
                        .collect(Collectors.toSet()))
                .build();
    }
}
