package com.n11.UserApp.dao.dto;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


public class RestaurantDTOTest {

    @Test
    public void testRestaurantDTOConstructor() {
        String id = "123456";
        String name = "SOLE SUSHI";
        String email = "sole.sushi@email.com";
        String createdAt = LocalDateTime.now().toString();
        Double latitude = 40.7128;
        Double longitude = -74.0060;
        Double score = 4.5;
        String category = "Asian";

        RestaurantDTO dto = new RestaurantDTO(id, name, email, createdAt, latitude, longitude, score, category, 0, "");

        assertEquals(id, dto.getId());
        assertEquals(name, dto.getName());
        assertEquals(latitude, dto.getLatitude());
        assertEquals(longitude, dto.getLongitude());
        assertEquals(score, dto.getScore());
        assertEquals(createdAt, dto.getCreatedAt());
        assertEquals(email, dto.getEmail());
        assertEquals(category, dto.getCategory());
    }

    @Test
    public void testRestaurantDTOGettersAndSetters() {
        RestaurantDTO dto = new RestaurantDTO();

        String id = "123456";
        String name = "SOLE SUSHI";
        Double latitude = 40.7128;
        Double longitude = -74.0060;
        Double score = 4.5;
        String email = "sole.sushi@email.com";
        String category = "Asian";
        String createdAt = LocalDateTime.now().toString();

        dto.setId(id);
        dto.setCategory(category.toString());
        dto.setName(name);
        dto.setLatitude(latitude);
        dto.setLongitude(longitude);
        dto.setScore(score);
        dto.setEmail(email);
        dto.setCreatedAt(createdAt);

        assertEquals(id, dto.getId());
        assertEquals(name, dto.getName());
        assertEquals(latitude, dto.getLatitude());
        assertEquals(longitude, dto.getLongitude());
        assertEquals(score, dto.getScore());
        assertEquals(createdAt, dto.getCreatedAt());
        assertEquals(email, dto.getEmail());
        assertEquals(category, dto.getCategory());
    }

}
