package com.n11.UserApp.dao.dto;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class UserReviewDTOTest {

    @Test
    public void testUserReviewDTOConstructor() {
        UUID userId = UUID.randomUUID();
        String restaurantId = "123456";
        int rate = 4;

        UserReviewDTO dto = new UserReviewDTO(userId, restaurantId, rate);


    }

    @Test
    public void testUserReviewDTOGettersAndSetters() {
        UserReviewDTO dto = new UserReviewDTO();

        UUID userId = UUID.randomUUID();
        String restaurantId = "123456";
        int rate = 4;

        dto.setUserId(userId);
        dto.setRestaurantId(restaurantId);
        dto.setRate(rate);

        assertEquals(userId, dto.getUserId());
        assertEquals(restaurantId, dto.getRestaurantId());
        assertEquals(rate, dto.getRate());
    }


}