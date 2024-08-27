package com.rochman.book_rent.service;

import com.rochman.book_rent.dto.request.CreateCategoryRequest;
import com.rochman.book_rent.dto.request.UpdateCategoryRequest;
import com.rochman.book_rent.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse create(CreateCategoryRequest createCategoryRequest);
    CategoryResponse update(UpdateCategoryRequest UpdateCategoryRequest);
    CategoryResponse getById(String id);
    List<CategoryResponse> getAll();
    void delete(String id);
}
