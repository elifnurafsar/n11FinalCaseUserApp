package com.n11.UserApp.service;

import com.n11.UserApp.common.CustomException;
import com.n11.UserApp.dao.entity.User;
import com.n11.UserApp.repository.UserRepository;
import com.n11.UserApp.request.user.CreateUserRequest;
import com.n11.UserApp.request.user.UpdateUserRequest;
import com.n11.UserApp.response.UserResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testSaveUser_Success() {
        CreateUserRequest request = new CreateUserRequest();
        request.setName("John");
        request.setEmail("john@example.com");
        when(userRepository.save(any())).thenAnswer(invocation -> {
            User user = invocation.getArgument(0);
            user.setId(UUID.randomUUID());
            user.setCreatedAt(LocalDateTime.now());
            return user;
        });

        UserResponse response = userService.saveUser(request);

        assertNotNull(response);
        assertNotNull(response.getId());
        assertEquals(request.getName(), response.getName());
        assertEquals(request.getEmail(), response.getEmail());
        assertNotNull(response.getCreatedAt());
    }

    @Test
    void testGetUserById_Success() throws CustomException {
        UUID userId = UUID.randomUUID();
        User user = new User();
        user.setId(userId);
        user.setName("John");
        user.setEmail("john@example.com");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        UserResponse response = userService.getUserById(userId);

        assertNotNull(response);
        assertEquals(user.getId(), response.getId());
        assertEquals(user.getName(), response.getName());
        assertEquals(user.getEmail(), response.getEmail());
    }

    @Test
    void testGetUserById_Failure_UserNotFound() {
        UUID userId = UUID.randomUUID();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> userService.getUserById(userId));
    }

    @Test
    void testDelete_Failure_UserNotFound() {
        UUID userId = UUID.randomUUID();
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> userService.delete(userId));
    }

    @Test
    void testUpdateUser_Failure_UserNotFound() {
        UpdateUserRequest request = new UpdateUserRequest();
        request.setId(UUID.randomUUID());
        when(userRepository.findById(request.getId())).thenReturn(Optional.empty());

        assertThrows(CustomException.class, () -> userService.updateUser(request));
    }


}