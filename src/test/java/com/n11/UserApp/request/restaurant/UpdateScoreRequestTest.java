package com.n11.UserApp.request.restaurant;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateScoreRequestTest {

    @Test
    public void testConstructorAndGetters() {
        String id = "12345";
        int score = 4;

        UpdateScoreRequest updateScoreRequest = new UpdateScoreRequest(id, score);

        assertEquals(id, updateScoreRequest.getId());
        assertEquals(score, updateScoreRequest.getScore());
    }

    @Test
    public void testSetter() {
        String id = "12345";
        int score = 4;
        UpdateScoreRequest updateScoreRequest = new UpdateScoreRequest();

        updateScoreRequest.setId(id);
        updateScoreRequest.setScore(score);

        assertEquals(id, updateScoreRequest.getId());
        assertEquals(score, updateScoreRequest.getScore());
    }

}