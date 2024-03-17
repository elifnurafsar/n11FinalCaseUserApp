package com.n11.UserApp.controller;


import com.n11.UserApp.dao.entity.User;
import com.n11.UserApp.dao.mapper.UserMapper;
import com.n11.UserApp.request.user.UpdateUserRequest;
import com.n11.UserApp.response.UserResponse;
import com.n11.UserApp.service.UserService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(UserController.class)
public class UserControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    @Test
    public void testCreateUser_Success() throws Exception {
        when(userMapper.requestToEntity(any())).thenReturn(new User());

        when(userService.saveUser(any())).thenReturn(new UserResponse());

        String requestBody = "{ \"name\": \"John\", \"surname\": \"Doe\", \"email\": \"john@example.com\", \"latitude\": 40.7128, \"longitude\": -74.0060 }";

        mockMvc.perform(MockMvcRequestBuilders.post("/User")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    @SneakyThrows
    public void testGetById_Success() {
        UUID userId = UUID.randomUUID();
        UserResponse userResponse = new UserResponse();
        when(userService.getUserById(userId)).thenReturn(userResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/User/find-by-id")
                        .param("id", userId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @SneakyThrows
    public void testGetAll_Success(){
        UserResponse userResponse = new UserResponse();
        when(userService.getAllUsers()).thenReturn(Collections.singletonList(userResponse));

        mockMvc.perform(MockMvcRequestBuilders.get("/User")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @SneakyThrows
    public void testUpdateUser_Success() {
        UpdateUserRequest updateUserRequest = new UpdateUserRequest();
        UserResponse userResponse = new UserResponse();
        when(userService.updateUser(any(UpdateUserRequest.class))).thenReturn(userResponse);

        mockMvc.perform(MockMvcRequestBuilders.put("/User")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @SneakyThrows
    public void testGetByName_Success() {
        String name = "John";
        UserResponse userResponse = new UserResponse();
        when(userService.getUserByName(name)).thenReturn(Collections.singletonList(userResponse));

        mockMvc.perform(MockMvcRequestBuilders.get("/User/find-by-name")
                        .param("name", name)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @SneakyThrows
    public void testDelete_Success(){
        UUID userId = UUID.randomUUID();
        mockMvc.perform(MockMvcRequestBuilders.delete("/User")
                        .param("id", userId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}