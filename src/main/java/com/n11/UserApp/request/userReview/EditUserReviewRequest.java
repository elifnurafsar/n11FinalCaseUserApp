package com.n11.UserApp.request.userReview;

import com.n11.UserApp.common.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EditUserReviewRequest extends BaseRequest {
    private UUID id;
    private int rate;
    private String comment;
}
