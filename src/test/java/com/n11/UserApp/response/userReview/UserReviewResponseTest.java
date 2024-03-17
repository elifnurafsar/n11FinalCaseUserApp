package com.n11.UserApp.response.userReview;


import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UserReviewResponseTest {

    @Test
    public void testUserReviewResponseConstructor() {
        UUID id = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        String restaurantId = "123456";
        int rate = 4;
        String comment = "Great experience.";

        UserReviewResponse response = new UserReviewResponse(id, userId, restaurantId, rate, comment, 0, "");

        assertEquals(id, response.getId());
        assertEquals(userId, response.getUserId());
        assertEquals(restaurantId, response.getRestaurantId());
        assertEquals(rate, response.getRate());
        assertEquals(comment, response.getComment());
    }

    @Test
    public void testUserReviewResponseGettersAndSetters() {
        UserReviewResponse response = new UserReviewResponse();

        UUID id = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        String restaurantId = "123456";
        int rate = 4;
        String comment = "Great experience.";

        response.setId(id);
        response.setUserId(userId);
        response.setRestaurantId(restaurantId);
        response.setRate(rate);
        response.setComment(comment);

        assertEquals(id, response.getId());
        assertEquals(userId, response.getUserId());
        assertEquals(restaurantId, response.getRestaurantId());
        assertEquals(rate, response.getRate());
        assertEquals(comment, response.getComment());
    }

}
