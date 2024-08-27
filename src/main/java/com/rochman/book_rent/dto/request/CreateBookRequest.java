package com.rochman.book_rent.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateBookRequest {
    @NotNull(message = "Stock is required")
    @Min(value = 0, message = "Stock must be greater or equals 0")
    private Integer stock;

    @NotBlank(message = "Title is required")
    private String title;

    @NotEmpty(message = "Category IDs cannot be empty")
    private Set<String> categoryIds;
}
