package com.n11.UserApp.service;

import com.n11.UserApp.client.RestaurantClient;
import com.n11.UserApp.common.CustomException;
import com.n11.UserApp.dao.dto.RestaurantDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RestaurantServiceTest {

    @Mock
    private RestaurantClient restaurantClient;

    @InjectMocks
    private RestaurantService restaurantService;

    @Test
    void testGetAll_Success() {
        List<RestaurantDTO> expected = new ArrayList<>();
        ResponseEntity<List<RestaurantDTO>> responseEntity = new ResponseEntity<>(expected, HttpStatus.OK);
        when(restaurantClient.getAll()).thenReturn(responseEntity);

        List<RestaurantDTO> result = restaurantService.getAll();

        // Assert
        assertEquals(expected, result);
        verify(restaurantClient, times(1)).getAll();
    }

    @Test
    void testGetAll_Failure() {
        when(restaurantClient.getAll()).thenThrow(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR));

        assertThrows(Exception.class, () -> restaurantService.getAll());
        verify(restaurantClient, times(1)).getAll();
    }

    @Test
    void testFindTop3Restaurants_Failure() {
        double latitude = 46.4725;
        double longitude = 7.2847;
        when(restaurantClient.findRestaurantsWithin10Kilometers(latitude, longitude)).thenThrow(new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR));

        assertThrows(Exception.class, () -> restaurantService.findTop3Restaurants(latitude, longitude));
        verify(restaurantClient, times(1)).findRestaurantsWithin10Kilometers(latitude, longitude);
    }

    @Test
    void testFindTop3Restaurants_NoRestaurantsNearby() {
        double latitude = 46.4725;
        double longitude = 7.2847;
        List<RestaurantDTO> emptyList = new ArrayList<>();
        ResponseEntity<List<RestaurantDTO>> responseEntity = new ResponseEntity<>(emptyList, HttpStatus.OK);
        when(restaurantClient.findRestaurantsWithin10Kilometers(latitude, longitude)).thenReturn(responseEntity);

        assertThrows(CustomException.class, () -> restaurantService.findTop3Restaurants(latitude, longitude));
        verify(restaurantClient, times(1)).findRestaurantsWithin10Kilometers(latitude, longitude);
    }

    @Test
    void testFindTop3Restaurants_Success() {
        double latitude = 46.4725;
        double longitude = 7.2847;
        String createdAt = LocalDateTime.now().toString();

        List<RestaurantDTO> nearbyRestaurants = new ArrayList<>();
        nearbyRestaurants.add(new RestaurantDTO("1", "Restaurant 1", "email1@example.com", createdAt, 46.4730, 7.2850, 4.0, "Category 1", 0, ""));
        nearbyRestaurants.add(new RestaurantDTO("2", "Restaurant 2", "email2@example.com", createdAt, 46.4735, 7.2855, 4.5, "Category 2", 0, ""));
        nearbyRestaurants.add(new RestaurantDTO("3", "Restaurant 3", "email3@example.com", createdAt, 46.4740, 7.2860, 3.5, "Category 3", 0, ""));
        ResponseEntity<List<RestaurantDTO>> responseEntity = new ResponseEntity<>(nearbyRestaurants, HttpStatus.OK);
        when(restaurantClient.findRestaurantsWithin10Kilometers(latitude, longitude)).thenReturn(responseEntity);

        List<RestaurantDTO> result = restaurantService.findTop3Restaurants(latitude, longitude);

        assertEquals(3, result.size());
        verify(restaurantClient, times(1)).findRestaurantsWithin10Kilometers(latitude, longitude);
    }

}
