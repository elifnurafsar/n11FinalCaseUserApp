package com.n11.UserApp.controller;


import com.n11.UserApp.common.BaseController;
import com.n11.UserApp.common.CustomException;
import com.n11.UserApp.dao.dto.UserReviewDetailDTO;
import com.n11.UserApp.request.userReview.CreateUserReviewRequest;
import com.n11.UserApp.request.userReview.EditUserReviewRequest;
import com.n11.UserApp.response.userReview.UserReviewListResponse;
import com.n11.UserApp.response.userReview.UserReviewResponse;
import com.n11.UserApp.service.UserReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/Review")
public class UserReviewController implements BaseController<CreateUserReviewRequest> {

    private final UserReviewService userReviewService;

    @Autowired
    public UserReviewController(UserReviewService userReviewService) {
        this.userReviewService = userReviewService;
    }

    @GetMapping("/find-all-by-user-id")
    public ResponseEntity<UserReviewListResponse> getByUserId(@RequestParam("userId") UUID userId) {
        UserReviewListResponse userReviewListResponse = userReviewService.getByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(userReviewListResponse);
    }

    @Override
    @GetMapping
    public ResponseEntity<UserReviewListResponse> getAll() {
        UserReviewListResponse userReviewListResponse = userReviewService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(userReviewListResponse);
    }

    @GetMapping("/find-all-by-restaurant-id")
    public ResponseEntity<UserReviewListResponse> findAllByRestaurantId(@RequestParam("restaurantId") String restaurantId) {
        try{
            UserReviewListResponse userReviewListResponse = userReviewService.findAllByRestaurantId(restaurantId);
            return ResponseEntity.status(HttpStatus.OK).body(userReviewListResponse);
        }
        catch (CustomException e) {
            // Spring handles it
            throw e;
        }
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<UserReviewDetailDTO> getById(@RequestParam("id") UUID id){
        try{
            UserReviewDetailDTO userReviewDetailDTO = userReviewService.findById(id);
            return ResponseEntity.status(HttpStatus.OK).body(userReviewDetailDTO);
        }
        catch (CustomException e) {
            // Spring handles it
            throw e;
        }
    }

    @Override
    @PostMapping
    public ResponseEntity<UserReviewResponse> create(@RequestBody CreateUserReviewRequest userReviewRequest) {
        try {
            UserReviewResponse userReviewResponse = userReviewService.save(userReviewRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(userReviewResponse);
        }
        catch (CustomException e) {
            // Spring handles it
            throw e;
        }
    }

    @PutMapping
    public ResponseEntity<UserReviewResponse> editComment(@RequestBody EditUserReviewRequest editUserReviewRequest) {
        try {
            UserReviewResponse userReviewResponse = userReviewService.updateReview(editUserReviewRequest);
            return ResponseEntity.status(HttpStatus.OK).body(userReviewResponse);
        }
        catch (CustomException e) {
            // Spring handles it
            throw e;
        }
    }

    @DeleteMapping
    public HttpStatus delete(@RequestParam("id") UUID id){
        try {
            userReviewService.delete(id);
            return HttpStatus.OK;
        } catch (CustomException e) {
            // Spring handles it
            throw e;
        }
    }
}

