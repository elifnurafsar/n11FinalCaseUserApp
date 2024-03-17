package com.n11.UserApp.service;

import com.n11.UserApp.client.RestaurantClient;
import com.n11.UserApp.common.CustomException;
import com.n11.UserApp.common.enums.ErrorMessages;
import com.n11.UserApp.dao.dto.RestaurantDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Log4j2
public class RestaurantService {

    private final RestaurantClient restaurantClient;

    @Transactional(readOnly = true)
    public List<RestaurantDTO> getAll(){
        ResponseEntity<List<RestaurantDTO>> restaurantDTOS = restaurantClient.getAll();
        if(restaurantDTOS.getStatusCode() == HttpStatus.OK){
            return restaurantDTOS.getBody();
        }
        else
            throw new CustomException(ErrorMessages.RESTAURANT_CLIENT_EXCEPTION);
    }

    public List<RestaurantDTO> findTop3Restaurants(double latitude, double longitude) throws CustomException{
        List<RestaurantDTO> restaurantDTOS = findRestaurantsWithin10Kilometers(latitude, longitude);

        if(restaurantDTOS.isEmpty())
            throw new CustomException(ErrorMessages.NO_AVAILABLE_RESTAURANTS_NEARBY);

        return filterTopRestaurants(latitude, longitude, restaurantDTOS);
    }

    private List<RestaurantDTO> filterTopRestaurants(double userLatitude, double userLongitude, List<RestaurantDTO> topRestaurants) {

        Map<Double, RestaurantDTO> restaurantsWithWeightedScore = new TreeMap<>(Collections.reverseOrder());
        for (RestaurantDTO restaurant : topRestaurants) {
            double distance = calculateDistance(restaurant.getLatitude(), restaurant.getLongitude(),
                    userLatitude, userLongitude);

            double weightedScore = (restaurant.getScore() * 0.7) + (calculateProximityScore(distance) * 0.3);
            restaurantsWithWeightedScore.put(weightedScore, restaurant);
        }

        return selectTop3(restaurantsWithWeightedScore);
    }

    private List<RestaurantDTO> selectTop3(Map<Double, RestaurantDTO> map){
        List<RestaurantDTO> top3Restaurants = new ArrayList<>();
        int count = 0;
        int sze = map.size();
        for (Map.Entry<Double, RestaurantDTO> entry : map.entrySet()) {
            top3Restaurants.add(entry.getValue());
            count++;
            if (count >= 3 || count == sze) {
                break;
            }
        }
        return top3Restaurants;
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // Implementation of Haversine formula to calculate distance between two coordinates
        double earthRadius = 6371; // Earth's radius in kilometers
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return earthRadius * c;
    }

    private double calculateProximityScore(double distance) {
        return 1.0 - (distance / 10);
    }


    private List<RestaurantDTO> findRestaurantsWithin10Kilometers(double latitude, double longitude ){
        ResponseEntity<List<RestaurantDTO>> restaurantDTOS = restaurantClient.findRestaurantsWithin10Kilometers(latitude, longitude);
        if(restaurantDTOS.getStatusCode() == HttpStatus.OK){
            return restaurantDTOS.getBody();
        }
        else
            throw new CustomException(ErrorMessages.RESTAURANT_CLIENT_EXCEPTION);
    }
}
