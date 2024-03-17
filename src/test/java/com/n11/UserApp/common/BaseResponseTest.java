package com.n11.UserApp.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BaseResponseTest {

    @Test
    public void testBaseResponse() {
        int expectedErrCode = 400;
        String expectedErrMessage = "Error!";

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setErrCode(expectedErrCode);
        baseResponse.setErrMessage(expectedErrMessage);

        assertEquals(expectedErrCode, baseResponse.getErrCode());
        assertEquals(expectedErrMessage, baseResponse.getErrMessage());
    }
}