package com.n11.UserApp.controller;


import com.n11.UserApp.dao.dto.UserReviewDetailDTO;
import com.n11.UserApp.request.userReview.CreateUserReviewRequest;
import com.n11.UserApp.request.userReview.EditUserReviewRequest;
import com.n11.UserApp.response.userReview.UserReviewListResponse;
import com.n11.UserApp.response.userReview.UserReviewResponse;
import com.n11.UserApp.service.UserReviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.mockito.Mockito.when;

@WebMvcTest(UserReviewController.class)
public class UserReviewControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserReviewService userReviewService;

    @Test
    public void testGetByUserId_Success() throws Exception {
        UUID userId = UUID.randomUUID();
        UserReviewListResponse userReviewListResponse = new UserReviewListResponse();
        when(userReviewService.getByUserId(userId)).thenReturn(userReviewListResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/Review/find-all-by-user-id")
                        .param("userId", userId.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetAll_Success() throws Exception {
        UserReviewListResponse userReviewListResponse = new UserReviewListResponse();
        when(userReviewService.getAll()).thenReturn(userReviewListResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/Review")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testFindAllByRestaurantId_Success() throws Exception {
        String restaurantId = "restaurantId";
        UserReviewListResponse userReviewListResponse = new UserReviewListResponse();
        when(userReviewService.findAllByRestaurantId(restaurantId)).thenReturn(userReviewListResponse);

        mockMvc.perform(MockMvcRequestBuilders.get("/Review/find-all-by-restaurant-id")
                        .param("restaurantId", restaurantId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testGetById_Success() throws Exception {
        UUID id = UUID.randomUUID();
        UserReviewDetailDTO userReviewDetailDTO = new UserReviewDetailDTO();
        when(userReviewService.findById(id)).thenReturn(userReviewDetailDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/Review/find-by-id")
                        .param("id", id.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testCreate_Success() throws Exception {
        CreateUserReviewRequest userReviewRequest = new CreateUserReviewRequest();
        UserReviewResponse userReviewResponse = new UserReviewResponse();
        when(userReviewService.save(userReviewRequest)).thenReturn(userReviewResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/Review")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }

    @Test
    public void testEditComment_Success() throws Exception {
        EditUserReviewRequest editUserReviewRequest = new EditUserReviewRequest();
        UserReviewResponse userReviewResponse = new UserReviewResponse();
        when(userReviewService.updateReview(editUserReviewRequest)).thenReturn(userReviewResponse);

        mockMvc.perform(MockMvcRequestBuilders.put("/Review")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void testDelete_Success() throws Exception {
        UUID id = UUID.randomUUID();
        mockMvc.perform(MockMvcRequestBuilders.delete("/Review")
                        .param("id", id.toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}