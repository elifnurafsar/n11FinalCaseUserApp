package com.n11.UserApp.controller;

import com.n11.UserApp.common.CustomException;
import com.n11.UserApp.common.enums.ErrorMessages;
import com.n11.UserApp.dao.dto.RestaurantDTO;
import com.n11.UserApp.service.RestaurantService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestaurantSuggestionControllerTest {

    @Mock
    private RestaurantService restaurantService;

    @InjectMocks
    private RestaurantSuggestionController restaurantSuggestionController;

    @Test
    void testFindTop3_Success() {
        double latitude = 42.0;
        double longitude = 42.0;
        List<RestaurantDTO> expectedResponse = new ArrayList<>();
        when(restaurantService.findTop3Restaurants(latitude, longitude)).thenReturn(expectedResponse);

        ResponseEntity<List<RestaurantDTO>> responseEntity = restaurantSuggestionController.findTop3(latitude, longitude);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedResponse, responseEntity.getBody());
    }

    @Test
    void testFindTop3_CustomExceptionThrown() {
        double latitude = 42.0;
        double longitude = 42.0;
        CustomException customException = new CustomException(ErrorMessages.DEFAULT);
        when(restaurantService.findTop3Restaurants(latitude, longitude)).thenThrow(customException);

        assertThrows(CustomException.class, () -> restaurantSuggestionController.findTop3(latitude, longitude));
    }


}
