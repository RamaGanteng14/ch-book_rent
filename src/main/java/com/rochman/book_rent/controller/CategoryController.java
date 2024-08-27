package com.rochman.book_rent.controller;

import com.rochman.book_rent.constant.PathUrl;
import com.rochman.book_rent.dto.request.CreateCategoryRequest;
import com.rochman.book_rent.dto.request.UpdateCategoryRequest;
import com.rochman.book_rent.dto.response.CategoryResponse;
import com.rochman.book_rent.service.CategoryService;
import com.rochman.book_rent.dto.response.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(PathUrl.CATEGORY)
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CommonResponse<CategoryResponse>> createCategory(@RequestBody CreateCategoryRequest createCategoryRequest) {
        CategoryResponse categoryResponse = categoryService.create(createCategoryRequest);
        CommonResponse<CategoryResponse> response = CommonResponse.<CategoryResponse>builder()
                .message("Category created successfully")
                .statusCode(HttpStatus.CREATED.value())
                .data(categoryResponse)
                .build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<CommonResponse<CategoryResponse>> updateCategory(@RequestBody UpdateCategoryRequest updateCategoryRequest) {
        CategoryResponse categoryResponse = categoryService.update(updateCategoryRequest);
        CommonResponse<CategoryResponse> response = CommonResponse.<CategoryResponse>builder()
                .message("Category updated successfully")
                .statusCode(HttpStatus.OK.value())
                .data(categoryResponse)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<CategoryResponse>> getCategoryById(@PathVariable String id) {
        CategoryResponse categoryResponse = categoryService.getById(id);
        CommonResponse<CategoryResponse> response = CommonResponse.<CategoryResponse>builder()
                .message("Category fetch successfully")
                .statusCode(HttpStatus.OK.value())
                .data(categoryResponse)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<CommonResponse<List<CategoryResponse>>> getAllCategories() {
        List<CategoryResponse> categories = categoryService.getAll();
        CommonResponse<List<CategoryResponse>> response = CommonResponse.<List<CategoryResponse>>builder()
                .message("Categories get all successfully")
                .statusCode(HttpStatus.OK.value())
                .data(categories)
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse<Void>> deleteCategory(@PathVariable String id) {
        categoryService.delete(id);
        CommonResponse<Void> response = CommonResponse.<Void>builder()
                .message("Category deleted successfully")
                .statusCode(HttpStatus.NO_CONTENT.value())
                .build();
        return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
    }
}
