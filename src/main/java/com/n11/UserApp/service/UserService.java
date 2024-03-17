package com.n11.UserApp.service;


import com.n11.UserApp.common.CustomException;
import com.n11.UserApp.common.enums.ErrorMessages;
import com.n11.UserApp.dao.entity.User;
import com.n11.UserApp.dao.mapper.UserMapper;
import com.n11.UserApp.repository.UserRepository;
import com.n11.UserApp.request.user.CreateUserRequest;
import com.n11.UserApp.request.user.UpdateUserRequest;
import com.n11.UserApp.response.UserResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Log4j2
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserResponse saveUser(CreateUserRequest userRequest) {
        User user = UserMapper.INSTANCE.requestToEntity(userRequest);
        user.setCreatedAt(LocalDateTime.now());
        User savedUser = userRepository.save(user);
        log.info("User with id: " + savedUser.getId() + " has saved at " + savedUser.getCreatedAt() + "!");
        return UserMapper.INSTANCE.entityToResponse(savedUser);
    }

    @Transactional(readOnly = true)
    public UserResponse getUserById(UUID userId) throws CustomException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorMessages.USER_NOT_FOUND));
        return UserMapper.INSTANCE.entityToResponse(user);
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return UserMapper.INSTANCE.entityListToResponseList(users);
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUserByName(String name) {
        List<User> users = userRepository.getByNameContaining(name);
        return UserMapper.INSTANCE.entityListToResponseList(users);
    }

    @Transactional
    public void delete(UUID id) throws CustomException {
        User user = getUserEntityById(id);
        try {
            userRepository.deleteById(id);
        }
        catch (Exception e){
            throw new CustomException(ErrorMessages.DEFAULT);
        }
        log.info("User with id: " + id + " has deleted at " + LocalDateTime.now() + "!");
    }

    @Transactional
    public UserResponse updateUser(UpdateUserRequest updateUserRequest){
        User user = getUserEntityById(updateUserRequest.getId());
        user.setName(updateUserRequest.getName());
        user.setSurname(updateUserRequest.getSurname());
        user.setEmail(updateUserRequest.getEmail());
        user.setLatitude(updateUserRequest.getLatitude());
        user.setLongitude(updateUserRequest.getLongitude());

        return UserMapper.INSTANCE.entityToResponse(user);
    }

    @Transactional(readOnly = true)
    public User getUserEntityById(UUID userId) throws CustomException {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorMessages.USER_NOT_FOUND));
        return user;
    }

}


