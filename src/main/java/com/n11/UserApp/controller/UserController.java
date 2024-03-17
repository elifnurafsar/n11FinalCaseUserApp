package com.n11.UserApp.controller;


import com.n11.UserApp.common.BaseController;
import com.n11.UserApp.common.CustomException;
import com.n11.UserApp.request.user.CreateUserRequest;
import com.n11.UserApp.request.user.UpdateUserRequest;
import com.n11.UserApp.response.UserResponse;
import com.n11.UserApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/User")
public class UserController implements BaseController<CreateUserRequest> {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Override
    @PostMapping
    public ResponseEntity<UserResponse> create(@RequestBody CreateUserRequest request) {
        UserResponse userResponse = userService.saveUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @Override
    @GetMapping("/find-by-id")
    public ResponseEntity<UserResponse> getById(@RequestParam("id") UUID id){
        try {
            UserResponse userResponse = userService.getUserById(id);
            return ResponseEntity.ok(userResponse);
        }
        catch (CustomException e){
            // Spring handles it
            throw e;
        }
    }

    @Override
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAll() {
        List<UserResponse> userResponses = userService.getAllUsers();
        return ResponseEntity.ok(userResponses);
    }

    @PutMapping
    public ResponseEntity<UserResponse> updateUser(@RequestBody UpdateUserRequest updateUserRequest) {
        try {
            UserResponse userResponse = userService.updateUser(updateUserRequest);
            return ResponseEntity.ok(userResponse);
        }
        catch (CustomException e) {
            // Spring handles it
            throw e;
        }
    }

    @GetMapping("/find-by-name")
    public ResponseEntity<List<UserResponse>> getByName(@RequestParam("name") String name) {
        List<UserResponse> userResponseList = userService.getUserByName(name);
        return ResponseEntity.ok(userResponseList);
    }

    @DeleteMapping
    public HttpStatus delete(@RequestParam("id") UUID id){
        try {
            userService.delete(id);
            return HttpStatus.OK;
        } catch (CustomException e) {
            // Spring handles it
            throw e;
        }
    }

}
