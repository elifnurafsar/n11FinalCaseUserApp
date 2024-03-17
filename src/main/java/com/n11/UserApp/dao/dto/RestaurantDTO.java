package com.n11.UserApp.dao.dto;

import com.n11.UserApp.common.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDTO extends BaseResponse {
    private String id;
    private String name;
    private String email;
    private String createdAt;
    private Double latitude;
    private Double longitude;
    private Double score;
    private String category;

    private int errCode = 0;
    private String errMessage = "";
}
