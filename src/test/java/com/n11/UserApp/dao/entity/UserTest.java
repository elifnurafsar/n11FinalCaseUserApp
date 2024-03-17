package com.n11.UserApp.dao.entity;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserTest {

    @Test
    public void testUserConstructor() {
        UUID id = UUID.randomUUID();
        String name = "John";
        String surname = "Doe";
        LocalDateTime createdAt = LocalDateTime.now();
        String email = "john.doe@example.com";
        Double latitude = 123.456;
        Double longitude = 789.012;

        User user = new User(id, name, surname, createdAt, email, latitude, longitude);

        assertEquals(id, user.getId());
        assertEquals(name, user.getName());
        assertEquals(surname, user.getSurname());
        assertEquals(createdAt, user.getCreatedAt());
        assertEquals(email, user.getEmail());
        assertEquals(latitude, user.getLatitude());
        assertEquals(longitude, user.getLongitude());
    }

    @Test
    public void testUserGettersAndSetters() {
        User user = new User();

        UUID id = UUID.randomUUID();
        String name = "John";
        String surname = "Doe";
        String email = "john.doe@example.com";
        LocalDateTime createdAt = LocalDateTime.now();
        Double latitude = 123.456;
        Double longitude = 789.012;

        user.setId(id);
        user.setName(name);
        user.setSurname(surname);
        user.setCreatedAt(createdAt);
        user.setEmail(email);
        user.setLatitude(latitude);
        user.setLongitude(longitude);

        assertEquals(name, user.getName());
        assertEquals(surname, user.getSurname());
        assertEquals(createdAt, user.getCreatedAt());
        assertEquals(email, user.getEmail());
        assertEquals(latitude, user.getLatitude());
        assertEquals(id, user.getId());
        assertEquals(longitude, user.getLongitude());
    }

}