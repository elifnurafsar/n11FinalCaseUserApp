package com.n11.UserApp.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.n11.UserApp.common.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse extends BaseResponse {
    private UUID id;
    private String name;
    private String surname;
    private String email;
    private LocalDateTime createdAt;
    private Double latitude;
    private Double longitude;

    @JsonIgnore
    private int errCode = 0;
    @JsonIgnore
    private String errMessage = "";
}
