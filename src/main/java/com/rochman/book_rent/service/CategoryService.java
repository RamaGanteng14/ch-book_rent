package com.rochman.book_rent.service;

import com.rochman.book_rent.dto.request.CategoryRequest;
import com.rochman.book_rent.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse create(CategoryRequest categoryRequest);
    CategoryResponse update(CategoryRequest categoryRequest);
    CategoryResponse getById(String id);
    List<CategoryResponse> getAll();
    void delete(String id);
}
