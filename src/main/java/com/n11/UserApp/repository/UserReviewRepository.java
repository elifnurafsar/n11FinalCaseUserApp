package com.n11.UserApp.repository;

import com.n11.UserApp.dao.entity.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface UserReviewRepository extends JpaRepository<UserReview, UUID> {
    List<UserReview> findByUserId(UUID userId);
    List<UserReview> findByRestaurantId(String restaurantId);

    @Query(value = "SELECT AVG(rate) AS average_rate FROM reviews r WHERE r.restaurant_id = :restaurantId", nativeQuery = true)
    double calculateAverageScoreOfRestaurants(@Param(value = "restaurantId") String restaurantId);

}
