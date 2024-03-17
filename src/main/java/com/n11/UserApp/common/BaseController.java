package com.n11.UserApp.common;

import org.springframework.http.ResponseEntity;

import java.util.UUID;

public interface BaseController<T extends BaseRequest> {

    ResponseEntity<?> create(T request) throws CustomException;

    ResponseEntity<?> getById(UUID id) throws CustomException;

    ResponseEntity<?> getAll();
}
