package com.n11.UserApp.controller;


import com.n11.UserApp.dao.dto.RestaurantDTO;
import com.n11.UserApp.service.RestaurantService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RestaurantSuggestionControllerIntegrationTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private RestaurantService restaurantService;

    @Test
    void testFindTop3_Success() {
        double latitude = 42.0;
        double longitude = 42.0;
        List<RestaurantDTO> expectedResponse = new ArrayList<>();
        when(restaurantService.findTop3Restaurants(latitude, longitude)).thenReturn(expectedResponse);

        ResponseEntity<List> responseEntity = restTemplate.getForEntity("http://localhost:" + port + "/Restaurants/find-top-three?latitude=" + latitude + "&longitude=" + longitude, List.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }


}
