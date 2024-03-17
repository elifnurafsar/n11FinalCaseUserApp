package com.n11.UserApp.controller;


import com.n11.UserApp.dao.dto.UserReviewDetailDTO;
import com.n11.UserApp.request.userReview.CreateUserReviewRequest;
import com.n11.UserApp.request.userReview.EditUserReviewRequest;
import com.n11.UserApp.response.userReview.UserReviewListResponse;
import com.n11.UserApp.response.userReview.UserReviewResponse;
import com.n11.UserApp.service.UserReviewService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class UserReviewControllerTest {

    @Mock
    private UserReviewService userReviewService;

    @InjectMocks
    private UserReviewController userReviewController;

    @Test
    public void testGetByUserId() {
        UUID userId = UUID.randomUUID();
        UserReviewListResponse expectedResponse = new UserReviewListResponse();

        when(userReviewService.getByUserId(userId)).thenReturn(expectedResponse);

        ResponseEntity<UserReviewListResponse> responseEntity = userReviewController.getByUserId(userId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());

        verify(userReviewService, times(1)).getByUserId(userId);
    }

    @Test
    public void testGetAll() {
        UserReviewListResponse expectedResponse = new UserReviewListResponse();

        when(userReviewService.getAll()).thenReturn(expectedResponse);

        ResponseEntity<UserReviewListResponse> responseEntity = userReviewController.getAll();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());

        verify(userReviewService, times(1)).getAll();
    }

    @Test
    public void testFindAllByRestaurantId() {
        String restaurantId = "123456";
        UserReviewListResponse expectedResponse = new UserReviewListResponse();

        when(userReviewService.findAllByRestaurantId(restaurantId)).thenReturn(expectedResponse);

        ResponseEntity<UserReviewListResponse> responseEntity = userReviewController.findAllByRestaurantId(restaurantId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());

        verify(userReviewService, times(1)).findAllByRestaurantId(restaurantId);
    }

    @Test
    public void testGetById() {
        UUID id = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        String userName = "John";
        String userSurname = "Doe";
        String userFullName = "John Doe";
        String restaurantId = "123456";
        String restaurantName = "SOLE SUSHI";
        int rate = 4;
        String comment = "Great experience.";

        UserReviewDetailDTO dto = new UserReviewDetailDTO(id, userId, userName, userSurname, userFullName, restaurantId, restaurantName, rate, comment);

        when(userReviewService.findById(id)).thenReturn(dto);

        ResponseEntity<UserReviewDetailDTO> responseEntity = userReviewController.getById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(dto, responseEntity.getBody());

        verify(userReviewService, times(1)).findById(id);
    }

    @Test
    public void testCreate() {
        UUID id = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        String restaurantId = "123456";
        int rate = 4;
        String comment = "Great experience.";

        CreateUserReviewRequest request = new CreateUserReviewRequest();

        UserReviewResponse userReviewResponse = new UserReviewResponse(id, userId, restaurantId, rate, comment, 0, "");

        when(userReviewService.save(request)).thenReturn(userReviewResponse);

        ResponseEntity<UserReviewResponse> responseEntity = userReviewController.create(request);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(userReviewResponse, responseEntity.getBody());

        verify(userReviewService, times(1)).save(request);
    }

    @Test
    public void testEditComment() {

        UUID id = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        String restaurantId = "123456";
        int rate = 4;
        String comment = "comment.";

        EditUserReviewRequest editUserReviewRequest = new EditUserReviewRequest(id, rate, comment);

        UserReviewResponse userReviewResponse = new UserReviewResponse(id, userId, restaurantId, rate, comment, 0, "");

        when(userReviewService.updateReview(editUserReviewRequest)).thenReturn(userReviewResponse);

        ResponseEntity<UserReviewResponse> responseEntity = userReviewController.editComment(editUserReviewRequest);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userReviewResponse, responseEntity.getBody());

        verify(userReviewService, times(1)).updateReview(editUserReviewRequest);
    }
}
