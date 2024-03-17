package com.n11.UserApp.dao.mapper;

import com.n11.UserApp.dao.entity.User;
import com.n11.UserApp.request.user.CreateUserRequest;
import com.n11.UserApp.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User requestToEntity(CreateUserRequest userRequest);

    UserResponse entityToResponse(User user);

    List<UserResponse> entityListToResponseList(List<User> users);
}
