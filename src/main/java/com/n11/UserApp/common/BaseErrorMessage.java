package com.n11.UserApp.common;

import java.io.Serializable;

public interface BaseErrorMessage extends Serializable {
    String getMessage();
    int getCode();
}
