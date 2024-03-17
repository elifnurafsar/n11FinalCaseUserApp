package com.n11.UserApp.request.userReview;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class CreateUserReviewRequestTest {

    @Test
    public void testCreateUserReviewRequestConstructor() {
        UUID userId = UUID.randomUUID();
        String restaurantId = "123456";
        int rate = 4;
        String comment = "Great experience.";

        CreateUserReviewRequest request = new CreateUserReviewRequest();

        request.setUserId(userId);
        request.setRestaurantId(restaurantId);
        request.setRate(rate);
        request.setComment(comment);
    }

    @Test
    public void testCreateUserReviewRequestGettersAndSetters() {
        CreateUserReviewRequest request = new CreateUserReviewRequest();

        UUID userId = UUID.randomUUID();
        String restaurantId = "123456";
        int rate = 4;
        String comment = "Great experience.";

        request.setUserId(userId);
        request.setRestaurantId(restaurantId);
        request.setRate(rate);
        request.setComment(comment);


        assertEquals(userId, request.getUserId());
        assertEquals(restaurantId, request.getRestaurantId());
        assertEquals(rate, request.getRate());
        assertEquals(comment, request.getComment());
    }
}