package com.n11.UserApp.dao.mapper;

import com.n11.UserApp.dao.entity.User;
import com.n11.UserApp.dao.entity.UserReview;
import com.n11.UserApp.request.userReview.CreateUserReviewRequest;
import com.n11.UserApp.response.userReview.UserReviewResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.UUID;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserReviewMapper {
    UserReviewMapper INSTANCE = Mappers.getMapper(UserReviewMapper.class);

    UserReview requestToEntity(CreateUserReviewRequest userReviewRequest);

    @Mapping(target = "userId", source = "user")
    UserReviewResponse entityToResponse(UserReview userReview);

    default UUID mapUser(User user){
        return user != null ? user.getId() : null;
    }

    default UUID mapUser(UserReview userReview) {
        return userReview != null ? mapUser(userReview.getUser()) : null;
    }
}
