package com.n11.UserApp.response.userReview;

import com.n11.UserApp.dao.dto.UserReviewDetailDTO;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class UserReviewListResponseTest {

    @Test
    void testConstructorAndGetters() {
        List<UserReviewDetailDTO> userReviewsList = new ArrayList<>();
        userReviewsList.add(mock(UserReviewDetailDTO.class));
        userReviewsList.add(mock(UserReviewDetailDTO.class));

        UserReviewListResponse response = new UserReviewListResponse(userReviewsList, 0, "");

        assertEquals(userReviewsList, response.getUserReviewsList());
        assertEquals(0, response.getErrCode());
        assertEquals("", response.getErrMessage());
    }

    @Test
    void testSetters() {
        UserReviewListResponse response = new UserReviewListResponse();

        List<UserReviewDetailDTO> userReviewsList = new ArrayList<>();
        userReviewsList.add(mock(UserReviewDetailDTO.class));
        userReviewsList.add(mock(UserReviewDetailDTO.class));

        response.setUserReviewsList(userReviewsList);
        response.setErrCode(123);
        response.setErrMessage("Error message!");

        assertEquals(userReviewsList, response.getUserReviewsList());
        assertEquals(123, response.getErrCode());
        assertEquals("Error message!", response.getErrMessage());
    }

    @Test
    void testBaseResponseMethods() {
        UserReviewListResponse response = new UserReviewListResponse();

        response.setErrCode(404);
        response.setErrMessage("Not Found!");

        assertEquals(404, response.getErrCode());
        assertEquals("Not Found!", response.getErrMessage());
    }
}
