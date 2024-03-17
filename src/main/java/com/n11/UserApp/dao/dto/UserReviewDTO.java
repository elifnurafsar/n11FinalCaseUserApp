package com.n11.UserApp.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReviewDTO {

    private UUID userId;
    private String restaurantId;
    private int rate;

}
