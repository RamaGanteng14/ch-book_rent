package com.rochman.book_rent.service.impl;

import com.rochman.book_rent.dto.request.CreateCategoryRequest;
import com.rochman.book_rent.dto.request.UpdateCategoryRequest;
import com.rochman.book_rent.dto.response.CategoryResponse;
import com.rochman.book_rent.entity.Category;
import com.rochman.book_rent.repository.CategoryRepository;
import com.rochman.book_rent.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponse create(CreateCategoryRequest createCategoryRequest) {
        Category category = Category.builder()
                .name(createCategoryRequest.getName())
                .build();
        categoryRepository.save(category);
        return toResponse(category);
    }

    @Override
    public CategoryResponse update(UpdateCategoryRequest updateCategoryRequest) {
        Optional<Category> optionalCategory = categoryRepository.findById(updateCategoryRequest.getId());
        if(optionalCategory.isEmpty()){
            throw new RuntimeException("category not found");
        }
            Category category = Category.builder()
                    .id(updateCategoryRequest.getId())
                    .name(updateCategoryRequest.getName())
                    .build();
            categoryRepository.saveAndFlush(category);
            return toResponse(category);
    }

    @Override
    public CategoryResponse getById(String id) {
        Category category = findCategoryById(id);
        return toResponse(category);
    }

    @Override
    public List<CategoryResponse> getAll() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

    }

    @Override
    public void delete(String id) {
        Category category = findCategoryById(id);
        categoryRepository.delete(category);
    }

    private Category findCategoryById(String id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Category not found"));
    }

    public CategoryResponse toResponse(Category category){
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
