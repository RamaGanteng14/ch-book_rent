//package com.rochman.book_rent.controller;
//
//import com.rochman.book_rent.constant.PathUrl;
//import com.rochman.book_rent.dto.request.CreateRentLogRequest;
//import com.rochman.book_rent.dto.response.RentLogResponse;
//import com.rochman.book_rent.service.RentLogService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequiredArgsConstructor
//@RequestMapping(PathUrl.RENTLOG)
//public class RentLogController {
//    private final RentLogService rentLogService;
//
////    @PostMapping
////    public ResponseEntity<RentLogResponse> createRentLog(@RequestBody CreateRentLogRequest request) {
////        RentLogResponse rentLogResponse = rentLogService.create(request);
////        return new ResponseEntity<>(rentLogResponse, HttpStatus.CREATED);
////    }
//
//    // Get All Rent Logs
//    @GetMapping
//    public ResponseEntity<List<RentLogResponse>> getAllRentLogs() {
//        List<RentLogResponse> rentLogResponses = rentLogService.getAll();
//        return new ResponseEntity<>(rentLogResponses, HttpStatus.OK);
//    }
//
//    // Return Book
//    @PutMapping("/{rentLogId}/return")
//    public ResponseEntity<RentLogResponse> returnBook(@PathVariable String rentLogId) {
//        RentLogResponse rentLogResponse = rentLogService.returnBook(rentLogId);
//        return new ResponseEntity<>(rentLogResponse, HttpStatus.OK);
//    }
//}
