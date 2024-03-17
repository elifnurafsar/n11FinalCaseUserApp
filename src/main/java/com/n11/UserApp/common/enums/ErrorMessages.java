package com.n11.UserApp.common.enums;

import com.n11.UserApp.common.BaseErrorMessage;

public enum ErrorMessages implements BaseErrorMessage {

    DEFAULT("An unexpected error occurred!", 4000),
    RESTAURANT_NOT_FOUND("Restaurant not found!", 4001),
    USER_NOT_FOUND("User not found!", 4002),
    REVIEW_NOT_FOUND("User review with given id not found.", 4003),
    SCORE_UPDATE_ERROR("Error occurred while updating the score. Please contact support for assistance.", 5005),
    RESTAURANT_CLIENT_EXCEPTION("Failed to connect to the Restaurant API.", 5006),
    NO_AVAILABLE_RESTAURANTS_NEARBY("Sorry, we couldn't find any restaurants nearby. Please try again with a different location.", 5007);

    private final String message;
    private final int code;

    ErrorMessages(String message, int code) {
        this.message = message;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        return message;
    }
}
