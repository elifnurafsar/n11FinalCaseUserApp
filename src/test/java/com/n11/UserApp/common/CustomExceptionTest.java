package com.n11.UserApp.common;

import com.n11.UserApp.common.enums.ErrorMessages;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomExceptionTest {

    @Test
    public void testCustomExceptionConstructorAndGetters() {

        CustomException customException = new CustomException(ErrorMessages.REVIEW_NOT_FOUND);

        assertEquals(ErrorMessages.REVIEW_NOT_FOUND.getMessage(), customException.getMessage());
        assertEquals(ErrorMessages.REVIEW_NOT_FOUND.getCode(), customException.getCode());
    }
}