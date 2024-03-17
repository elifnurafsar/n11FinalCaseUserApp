package com.n11.UserApp.controller;

import com.n11.UserApp.common.CustomException;
import com.n11.UserApp.dao.dto.RestaurantDTO;
import com.n11.UserApp.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Restaurants")
public class RestaurantSuggestionController {
    private final RestaurantService restaurantService;

    @Autowired
    public RestaurantSuggestionController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }


    @GetMapping("/find-top-three")
    public ResponseEntity<List<RestaurantDTO>> findTop3(@RequestParam("latitude") double latitude, @RequestParam("longitude") double longitude){
        try {
            List<RestaurantDTO> top3restaurants = restaurantService.findTop3Restaurants(latitude, longitude);
            return ResponseEntity.ok(top3restaurants);
        }
        catch (CustomException e){
            // Spring handles it
            throw e;
        }
    }


}
