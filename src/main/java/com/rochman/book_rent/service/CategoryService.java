package com.rochman.book_rent.service;

import com.rochman.book_rent.dto.request.CreateCategoryRequest;
import com.rochman.book_rent.dto.request.UpdateCategoryRequest;
import com.rochman.book_rent.dto.response.CategoryResponse;
import com.rochman.book_rent.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    CategoryResponse create(CreateCategoryRequest createCategoryRequest);
    CategoryResponse update(UpdateCategoryRequest UpdateCategoryRequest);
    CategoryResponse getById(String id);
    List<CategoryResponse> getAll();
    void delete(String id);
    Optional<Category> findById(String id);
}
