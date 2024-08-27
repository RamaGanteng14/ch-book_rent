package com.rochman.book_rent.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateBookRequest {
    @NotBlank(message = "Book ID is required")
    private String id;

    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Stock is required")
    @Min(value = 0, message = "Stock must be greater or equal to 0")
    private Integer stock;

    @NotNull(message = "Category IDs are required")
    private Set<String> categoryIds;
}
