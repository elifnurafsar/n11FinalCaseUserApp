package com.n11.UserApp.request;

import com.n11.UserApp.request.user.CreateUserRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class CreateUserRequestTest {

    @Test
    public void testUserConstructor() {
        String name = "John";
        String surname = "Doe";
        String email = "john.doe@example.com";
        Double latitude = 123.456;
        Double longitude = 789.012;

        CreateUserRequest userRequest = new CreateUserRequest(name, surname, email, latitude, longitude);

        assertEquals(name, userRequest.getName());
        assertEquals(surname, userRequest.getSurname());
        assertEquals(email, userRequest.getEmail());
        assertEquals(latitude, userRequest.getLatitude());
        assertEquals(longitude, userRequest.getLongitude());
    }

    @Test
    public void testUserGettersAndSetters() {
        CreateUserRequest userRequest = new CreateUserRequest();

        String name = "John";
        String surname = "Doe";
        String email = "john.doe@example.com";
        Double latitude = 123.456;
        Double longitude = 789.012;

        userRequest.setName(name);
        userRequest.setSurname(surname);
        userRequest.setEmail(email);
        userRequest.setLatitude(latitude);
        userRequest.setLongitude(longitude);

        assertEquals(name, userRequest.getName());
        assertEquals(surname, userRequest.getSurname());
        assertEquals(email, userRequest.getEmail());
        assertEquals(latitude, userRequest.getLatitude());
        assertEquals(longitude, userRequest.getLongitude());
    }

}