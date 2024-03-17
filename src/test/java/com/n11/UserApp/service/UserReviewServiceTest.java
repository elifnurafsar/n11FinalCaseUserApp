package com.n11.UserApp.service;

import com.n11.UserApp.client.RestaurantClient;
import com.n11.UserApp.common.CustomException;
import com.n11.UserApp.common.enums.ErrorMessages;
import com.n11.UserApp.dao.dto.RestaurantDTO;
import com.n11.UserApp.dao.dto.UserReviewDetailDTO;
import com.n11.UserApp.dao.entity.User;
import com.n11.UserApp.dao.entity.UserReview;
import com.n11.UserApp.repository.UserReviewRepository;
import com.n11.UserApp.request.restaurant.UpdateScoreRequest;
import com.n11.UserApp.request.userReview.CreateUserReviewRequest;
import com.n11.UserApp.request.userReview.EditUserReviewRequest;
import com.n11.UserApp.response.userReview.UserReviewListResponse;
import com.n11.UserApp.response.userReview.UserReviewResponse;
import feign.Response;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserReviewServiceTest {

    @Mock
    private UserReviewRepository userReviewRepository;

    @Mock
    private RestaurantClient restaurantClient;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserReviewService userReviewService;

    @Test
    void testGetByUserId_Success() {
        UUID userId = UUID.randomUUID();
        List<UserReview> userReviews = new ArrayList<>();
        when(userReviewRepository.findByUserId(userId)).thenReturn(userReviews);

        UserReviewListResponse response = userReviewService.getByUserId(userId);

        assertNotNull(response);
    }

    @Test
    @SneakyThrows
    void testGetAll_Success() {
        List<UserReview> userReviews = new ArrayList<>();

        when(userReviewRepository.findAll()).thenReturn(userReviews);

        UserReviewListResponse response = userReviewService.getAll();

        assertNotNull(response);
    }

    @Test
    void testFindAllByRestaurantId_Success() throws CustomException {
        String restaurantId = "restaurantId";
        RestaurantDTO restaurantDTO = new RestaurantDTO();
        when(userReviewRepository.findByRestaurantId(restaurantId)).thenReturn(new ArrayList<>());
        when(restaurantClient.findById(restaurantId)).thenReturn(new ResponseEntity<>(restaurantDTO, HttpStatus.OK));

        UserReviewListResponse response = userReviewService.findAllByRestaurantId(restaurantId);

        assertNotNull(response);
    }

    @Test
    void testFindById_Success() throws CustomException {
        UUID id = UUID.randomUUID();
        User user = new User();
        UserReview userReview = new UserReview();
        userReview.setUser(user);
        when(userReviewRepository.findById(id)).thenReturn(Optional.of(userReview));
        when(restaurantClient.findById(any())).thenReturn(new ResponseEntity<>(new RestaurantDTO(), HttpStatus.OK));

        UserReviewDetailDTO response = userReviewService.findById(id);

        assertNotNull(response);
    }


    @Test
    void testSave_Failure_UserNotFound() {
        CreateUserReviewRequest request = new CreateUserReviewRequest();
        request.setUserId(UUID.randomUUID());
        when(userService.getUserEntityById(request.getUserId())).thenThrow(new CustomException(ErrorMessages.USER_NOT_FOUND));

        assertThrows(CustomException.class, () -> userReviewService.save(request));
    }

}
