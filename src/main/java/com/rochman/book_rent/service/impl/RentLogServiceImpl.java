//package com.rochman.book_rent.service.impl;
//
//import com.rochman.book_rent.dto.request.CreateRentLogRequest;
//import com.rochman.book_rent.dto.response.BookResponse;
//import com.rochman.book_rent.dto.response.RentLogResponse;
//import com.rochman.book_rent.dto.response.UserResponse;
//import com.rochman.book_rent.entity.RentLog;
//import com.rochman.book_rent.repository.RentLogRepository;
//import com.rochman.book_rent.service.BookService;
//import com.rochman.book_rent.service.RentLogService;
//import com.rochman.book_rent.service.UserService;
//import com.rochman.book_rent.util.Validation;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class RentLogServiceImpl implements RentLogService {
//   private final RentLogRepository rentLogRepository;
//   private final BookService bookService;
//   private final UserService userService;
//   private final Validation validation;
//
//
////    @Override
////    public RentLogResponse create(CreateRentLogRequest rentLogRequest) {
////        validation.validate(rentLogRequest);
////
////        BookResponse books = bookService.getById(rentLogRequest.getBookId());
////        UserResponse user = userService.getById(rentLogRequest.getUserId());
////
////        Date rentDate = new Date();
////        Calendar calendar = Calendar.getInstance();
////        calendar.setTime(rentDate);
////        calendar.add(Calendar.DAY_OF_YEAR, 7);
////        Date returnDate = calendar.getTime();
////
////        RentLog rentLog = RentLog.builder()
////                .book(books)
////                .user(user)
////                .rentDate(rentDate)
////                .returnDate(returnDate)
////                .build();
////
////        RentLog savedRentLog = rentLogRepository.saveAndFlush(rentLog);
////
////        return toRentLogResponse(savedRentLog);
////    }
//
//    @Override
//    public List<RentLogResponse> getAll() {
//        List<RentLog> rentLogs = rentLogRepository.findAll();
//
//        return rentLogs.stream()
//                .map(this::toRentLogResponse)
//                .collect(Collectors.toList());
//    }
//
//
//    @Override
//    public RentLogResponse returnBook(String rentLogId) {
//        // Fetch rent log
//        RentLog rentLog = rentLogRepository.findById(rentLogId)
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Rent log not found"));
//        Date actualReturnDate = new Date();
//        rentLog.setActualReturnDate(actualReturnDate);
//
//        // Save updated rent log
//        RentLog updatedRentLog = rentLogRepository.saveAndFlush(rentLog);
//        return toRentLogResponse(updatedRentLog);
//    }
//
//    private RentLogResponse toRentLogResponse(RentLog rentLog) {
//        BookResponse bookResponse = BookResponse.builder()
//                .id(rentLog.getBook().getId())
//                .bookCode(rentLog.getBook().getBookCode())
//                .title(rentLog.getBook().getTitle())
//                .stock(rentLog.getBook().getStock())
//                .build();
//
//        UserResponse userResponse = UserResponse.builder()
//                .id(rentLog.getUser().getId())
//                .username(rentLog.getUser().getUsername())
////                .name(rentLog.getUser().getName())
//                .build();
//
//        return RentLogResponse.builder()
//                .id(rentLog.getId())
//                .book(bookResponse)
//                .user(userResponse)
//                .rentDate(rentLog.getRentDate())
//                .returnDate(rentLog.getReturnDate())
//                .actualReturnDate(rentLog.getActualReturnDate())
//                .build();
//    }
//}
