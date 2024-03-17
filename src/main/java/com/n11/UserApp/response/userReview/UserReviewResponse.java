package com.n11.UserApp.response.userReview;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.n11.UserApp.common.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserReviewResponse extends BaseResponse {

    private UUID id;
    private UUID userId;
    private String restaurantId;
    private int rate;
    private String comment;

    @JsonIgnore
    private int errCode = 0;
    @JsonIgnore
    private String errMessage = "";

}
