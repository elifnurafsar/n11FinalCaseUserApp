package com.n11.UserApp.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReviewDetailDTO {
    private UUID id;
    private UUID userId;
    private String userName;
    private String userSurname;
    private String userFullName;
    private String restaurantId;
    private String restaurantName;
    private int rate;
    private String comment;
}
