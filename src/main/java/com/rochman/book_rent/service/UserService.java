package com.rochman.book_rent.service;

import com.rochman.book_rent.dto.request.UserRequest;
import com.rochman.book_rent.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse create(UserRequest userRequest);
    UserResponse update(UserRequest userRequest);
    UserResponse getById(String id);
    List<UserResponse> getAll();
    void delete(String id);
}
