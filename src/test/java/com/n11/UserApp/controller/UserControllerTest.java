package com.n11.UserApp.controller;

import com.n11.UserApp.request.user.CreateUserRequest;
import com.n11.UserApp.response.UserResponse;
import com.n11.UserApp.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;


@ExtendWith(MockitoExtension.class)
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void testCreateUser() {
        UUID id = UUID.randomUUID();
        String name = "John";
        String surname = "Doe";
        LocalDateTime createdAt = LocalDateTime.now();
        String email = "john.doe@example.com";
        Double latitude = 123.456;
        Double longitude = 789.012;

        UserResponse userResponse = new UserResponse(id, name, surname, email, createdAt,  latitude, longitude, 0, "");

        CreateUserRequest request = new CreateUserRequest();

        when(userService.saveUser(request)).thenReturn(userResponse);

        ResponseEntity<UserResponse> responseEntity = userController.create(request);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(userResponse, responseEntity.getBody());

        verify(userService, times(1)).saveUser(request);
    }

    @Test
    public void testGetById() {
        UUID id = UUID.randomUUID();
        String name = "John";
        String surname = "Doe";
        LocalDateTime createdAt = LocalDateTime.now();
        String email = "john.doe@example.com";
        Double latitude = 123.456;
        Double longitude = 789.012;

        UserResponse userResponse = new UserResponse(id, name, surname, email, createdAt,  latitude, longitude, 0, "");

        when(userService.getUserById(id)).thenReturn(userResponse);

        ResponseEntity<UserResponse> responseEntity = userController.getById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userResponse, responseEntity.getBody());

        verify(userService, times(1)).getUserById(id);
    }

    @Test
    public void testGetAll() {
        List<UserResponse> expectedResponses = new ArrayList<>();

        UserResponse userResponse1 = new UserResponse(UUID.randomUUID(), "John", "Doe", "john.doe@example.com", LocalDateTime.now(),  123.456, 789.012, 0, "");
        UserResponse userResponse2 = new UserResponse(UUID.randomUUID(), "Jane", "Doe", "jane.doe@example.com", LocalDateTime.now(),  123.456, 789.012, 0, "");

        expectedResponses.add(userResponse1);
        expectedResponses.add(userResponse2);

        when(userService.getAllUsers()).thenReturn(expectedResponses);

        ResponseEntity<List<UserResponse>> responseEntity = userController.getAll();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponses, responseEntity.getBody());

        verify(userService, times(1)).getAllUsers();
    }

    @Test
    public void testGetByName() {
        String name = "John";

        List<UserResponse> expectedResponses = new ArrayList<>();

        UserResponse userResponse1 = new UserResponse(UUID.randomUUID(), "John", "Doe", "john.doe@example.com", LocalDateTime.now(),  123.456, 789.012, 0 ,"");
        UserResponse userResponse2 = new UserResponse(UUID.randomUUID(), "Jane", "Doe", "jane.doe@example.com", LocalDateTime.now(),  123.456, 789.012, 0, "");

        expectedResponses.add(userResponse1);
        expectedResponses.add(userResponse2);

        when(userService.getUserByName(name)).thenReturn(expectedResponses);

        ResponseEntity<List<UserResponse>> responseEntity = userController.getByName(name);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponses, responseEntity.getBody());

        verify(userService, times(1)).getUserByName(name);
    }

    @Test
    public void testDelete() {
        UUID id = UUID.randomUUID();

        HttpStatus expectedHttpStatus = HttpStatus.OK;

        HttpStatus actualHttpStatus = userController.delete(id);

        assertEquals(expectedHttpStatus, actualHttpStatus);

        verify(userService, times(1)).delete(id);
    }
}
