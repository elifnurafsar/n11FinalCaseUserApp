package com.n11.UserApp.request.userReview;


import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EditUserReviewRequestTest {

    @Test
    void testConstructor() {
        UUID id = UUID.randomUUID();
        int rate = 4;
        String comment = "comment.";

        EditUserReviewRequest editUserReviewRequest = new EditUserReviewRequest(id, rate, comment);

        assertNotNull(editUserReviewRequest);
        assertEquals(id, editUserReviewRequest.getId());
        assertEquals(rate, editUserReviewRequest.getRate());
        assertEquals(comment, editUserReviewRequest.getComment());
    }

    @Test
    void testSetters() {
        EditUserReviewRequest editUserReviewRequest = new EditUserReviewRequest();

        UUID id = UUID.randomUUID();
        int rate = 4;
        String comment = "comment.";

        editUserReviewRequest.setId(id);
        editUserReviewRequest.setRate(rate);
        editUserReviewRequest.setComment(comment);

        assertNotNull(editUserReviewRequest);
        assertEquals(id, editUserReviewRequest.getId());
        assertEquals(rate, editUserReviewRequest.getRate());
        assertEquals(comment, editUserReviewRequest.getComment());
    }
}
