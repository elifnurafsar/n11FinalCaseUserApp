package com.n11.UserApp.response;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class UserResponseTest {

    @Test
    public void testUserResponseResponseConstructor() {
        UUID id = UUID.randomUUID();
        String name = "John";
        String surname = "Doe";
        LocalDateTime createdAt = LocalDateTime.now();
        String email = "john.doe@example.com";
        Double latitude = 123.456;
        Double longitude = 789.012;

        UserResponse user = new UserResponse(id, name, surname, email, createdAt,  latitude, longitude, 0, "");

        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(surname, user.getSurname());
        assertEquals(createdAt, user.getCreatedAt());
        assertEquals(email, user.getEmail());
        assertEquals(latitude, user.getLatitude());
        assertEquals(longitude, user.getLongitude());
    }

    @Test
    public void testUserResponseResponseGettersAndSetters() {
        UserResponse userResponse = new UserResponse();

        UUID id = UUID.randomUUID();
        String name = "John";
        String surname = "Doe";
        String email = "john.doe@example.com";
        LocalDateTime createdAt = LocalDateTime.now();
        Double latitude = 123.456;
        Double longitude = 789.012;

        userResponse.setId(id);
        userResponse.setName(name);
        userResponse.setSurname(surname);
        userResponse.setCreatedAt(createdAt);
        userResponse.setEmail(email);
        userResponse.setLatitude(latitude);
        userResponse.setLongitude(longitude);

        assertEquals(name, userResponse.getName());
        assertEquals(surname, userResponse.getSurname());
        assertEquals(createdAt, userResponse.getCreatedAt());
        assertEquals(email, userResponse.getEmail());
        assertEquals(latitude, userResponse.getLatitude());
        assertEquals(id, userResponse.getId());
        assertEquals(longitude, userResponse.getLongitude());
    }
}
