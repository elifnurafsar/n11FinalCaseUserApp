package com.n11.UserApp.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomException extends RuntimeException {

    private int errCode;

    public CustomException(BaseErrorMessage baseErrorMessage) {
        super(baseErrorMessage.getMessage());
        errCode = baseErrorMessage.getCode();
    }

    public int getCode() {
        return errCode;
    }
}

