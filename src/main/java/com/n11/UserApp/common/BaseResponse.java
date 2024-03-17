package com.n11.UserApp.common;

import lombok.Data;

@Data
public class BaseResponse {
    private int errCode;
    private String errMessage;
}
