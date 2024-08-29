package com.rochman.book_rent.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RentLogResponse {
    private String id;
    private BookResponse book;
    private UserResponse user;
    private Date rentDate;
    private Date returnDate;
    private Date actualReturnDate;

}
