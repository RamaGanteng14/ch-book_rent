package com.rochman.book_rent.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponse {
    private String id;
    private String bookCode;
    private String title;
    private Integer stock;
}
