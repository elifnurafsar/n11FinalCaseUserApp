package com.n11.UserApp.dao.dto;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
public class UserReviewDetailDTOTest {

    @Test
    public void testUserReviewDetailDTOConstructor() {
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

        assertEquals(id, dto.getId());
        assertEquals(userId, dto.getUserId());
        assertEquals(userName, dto.getUserName());
        assertEquals(userSurname, dto.getUserSurname());
        assertEquals(userFullName, dto.getUserFullName());
        assertEquals(restaurantId, dto.getRestaurantId());
        assertEquals(restaurantName, dto.getRestaurantName());
        assertEquals(rate, dto.getRate());
        assertEquals(comment, dto.getComment());
    }

    @Test
    public void testUserReviewDetailDTOGettersAndSetters() {
        UserReviewDetailDTO dto = new UserReviewDetailDTO();

        UUID id = UUID.randomUUID();
        UUID userId = UUID.randomUUID();
        String userName = "John";
        String userSurname = "Doe";
        String userFullName = "John Doe";
        String restaurantId = "123456";
        String restaurantName = "SOLE SUSHI";
        int rate = 4;
        String comment = "Great experience.";

        dto.setId(id);
        dto.setUserId(userId);
        dto.setUserName(userName);
        dto.setUserSurname(userSurname);
        dto.setUserFullName(userFullName);
        dto.setRestaurantId(restaurantId);
        dto.setRestaurantName(restaurantName);
        dto.setRate(rate);
        dto.setComment(comment);

        assertEquals(id, dto.getId());
        assertEquals(userId, dto.getUserId());
        assertEquals(userName, dto.getUserName());
        assertEquals(userSurname, dto.getUserSurname());
        assertEquals(userFullName, dto.getUserFullName());
        assertEquals(restaurantId, dto.getRestaurantId());
        assertEquals(restaurantName, dto.getRestaurantName());
        assertEquals(rate, dto.getRate());
        assertEquals(comment, dto.getComment());
    }

}
