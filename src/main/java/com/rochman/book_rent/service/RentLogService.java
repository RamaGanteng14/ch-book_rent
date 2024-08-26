package com.rochman.book_rent.service;

import com.rochman.book_rent.dto.request.RentLogRequest;
import com.rochman.book_rent.dto.response.RentLogResponse;

import java.util.List;

public interface RentLogService {
    RentLogResponse create(RentLogRequest rentLogRequest);
    List<RentLogResponse> getAll();
}
