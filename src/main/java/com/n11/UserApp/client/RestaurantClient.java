package com.n11.UserApp.client;

import com.n11.UserApp.dao.dto.RestaurantDTO;
import com.n11.UserApp.request.restaurant.UpdateScoreRequest;
import feign.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "Restaurant", url = "http://localhost:8081/Restaurants")
public interface RestaurantClient {

    @GetMapping
    ResponseEntity<List<RestaurantDTO>> getAll();

    @GetMapping("/search")
    ResponseEntity<List<RestaurantDTO>> searchByName(@RequestParam("name") String name);

    @PutMapping("/score")
    Response updateScore(@RequestBody UpdateScoreRequest updateScoreRequest);

    @GetMapping("find-by-id")
    ResponseEntity<RestaurantDTO> findById(@RequestParam("id") String id);

    @GetMapping("/restaurants/within-10-kilometers/by-params")
    ResponseEntity<List<RestaurantDTO>> findRestaurantsWithin10Kilometers(@RequestParam("latitude") double latitude, @RequestParam("longitude") double longitude);


}