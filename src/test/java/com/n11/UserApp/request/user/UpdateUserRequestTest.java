package com.n11.UserApp.request.user;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

public class UpdateUserRequestTest {

    @Mock
    private UpdateUserRequest updateUserRequest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConstructorAndGetters() {
        UUID id = UUID.randomUUID();
        String name = "John";
        String surname = "Doe";
        String email = "john.doe@example.com";
        Double latitude = 12.3456;
        Double longitude = 78.9101;

        updateUserRequest = new UpdateUserRequest(id, name, surname, email, latitude, longitude);

        Assertions.assertEquals(id, updateUserRequest.getId());
        Assertions.assertEquals(name, updateUserRequest.getName());
        Assertions.assertEquals(surname, updateUserRequest.getSurname());
        Assertions.assertEquals(email, updateUserRequest.getEmail());
        Assertions.assertEquals(latitude, updateUserRequest.getLatitude());
        Assertions.assertEquals(longitude, updateUserRequest.getLongitude());
    }

    @Test
    public void testSetters() {
        UUID id = UUID.randomUUID();
        String name = "John";
        String surname = "Doe";
        String email = "john.doe@example.com";
        Double latitude = 12.3456;
        Double longitude = 78.9101;

        updateUserRequest = new UpdateUserRequest();

        updateUserRequest.setId(id);
        updateUserRequest.setName(name);
        updateUserRequest.setSurname(surname);
        updateUserRequest.setEmail(email);
        updateUserRequest.setLatitude(latitude);
        updateUserRequest.setLongitude(longitude);

        Assertions.assertEquals(id, updateUserRequest.getId());
        Assertions.assertEquals(name, updateUserRequest.getName());
        Assertions.assertEquals(surname, updateUserRequest.getSurname());
        Assertions.assertEquals(email, updateUserRequest.getEmail());
        Assertions.assertEquals(latitude, updateUserRequest.getLatitude());
        Assertions.assertEquals(longitude, updateUserRequest.getLongitude());
    }
}
