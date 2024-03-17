package com.n11.UserApp.request.userReview;

import com.n11.UserApp.common.BaseRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserReviewRequest extends BaseRequest {
    @NotNull
    private UUID userId;

    @NotBlank
    private String restaurantId;

    @Min(value = 1)
    @Max(value = 5)
    private int rate;

    private String comment;
}

