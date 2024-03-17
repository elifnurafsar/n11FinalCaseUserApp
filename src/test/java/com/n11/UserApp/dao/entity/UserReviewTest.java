package com.n11.UserApp.dao.entity;

import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


public class UserReviewTest {

    @Test
    public void testUserReviewConstructor() {
        UUID id = UUID.randomUUID();

        // Mocking User object
        User user = mock(User.class);

        String restaurantId = "123456";
        int rate = 4;
        String comment = "Great experience.";

        UserReview userReview = new UserReview(id, user, restaurantId, rate, comment);

        assertEquals(id, userReview.getId());
        assertEquals(user, userReview.getUser());
        assertEquals(restaurantId, userReview.getRestaurantId());
        assertEquals(rate, userReview.getRate());
        assertEquals(comment, userReview.getComment());
    }

    @Test
    public void testUserReviewGettersAndSetters() {
        UserReview userReview = new UserReview();

        // Mocking User object
        User user = mock(User.class);

        UUID id = UUID.randomUUID();
        String restaurantId = "123456";
        int rate = 5;
        String comment = "The ambiance of the restaurant was delightful, the food was exquisite, and the service was impeccable. " +
                "Definitely a must-visit for anyone looking for a memorable dining experience!";

        userReview.setId(id);
        userReview.setComment(comment);
        userReview.setUser(user);
        userReview.setRestaurantId(restaurantId);
        userReview.setRate(rate);

        assertEquals(id, userReview.getId());
        assertEquals(user, userReview.getUser());
        assertEquals(restaurantId, userReview.getRestaurantId());
        assertEquals(rate, userReview.getRate());
        assertEquals(comment, userReview.getComment());
    }

}
