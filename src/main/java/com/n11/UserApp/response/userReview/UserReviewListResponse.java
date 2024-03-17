package com.n11.UserApp.response.userReview;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.n11.UserApp.common.BaseResponse;
import com.n11.UserApp.dao.dto.UserReviewDetailDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserReviewListResponse extends BaseResponse {
    private List<UserReviewDetailDTO> userReviewsList;

    @JsonIgnore
    private int errCode = 0;
    @JsonIgnore
    private String errMessage = "";
}