package com.practice.BookUrStay.Controller;

import com.practice.BookUrStay.Advices.ApiResponse;
import com.practice.BookUrStay.DTO.UserReturnDTO;
import com.practice.BookUrStay.DTO.UserSignUpDTO;
import com.practice.BookUrStay.Service.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
@Slf4j
public class userControllerV1 {

    private final UserServiceImpl userServiceImpl;

    public userControllerV1(UserServiceImpl userServiceImpl) {
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping(path = "/getUserByEmail/{userEmail}")
    public ResponseEntity<ApiResponse<UserReturnDTO>> getUserByEmail(@PathVariable(name = "userEmail") String email) {
        log.info("Received request to get user by email: {}", email);
        ApiResponse<UserReturnDTO> response = new ApiResponse<>(userServiceImpl.getUserByEmail(email));
        log.info("Returning user details for email: {}", email);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = "/createUser")
    public ResponseEntity<ApiResponse<UserReturnDTO>> createUser(@Valid @RequestBody UserSignUpDTO userSignUpDTO) {
        log.info("Received request to create user with email: {}", userSignUpDTO.getEmail());
        UserReturnDTO userReturnDTO = userServiceImpl.createUser(userSignUpDTO);
        log.info("User created successfully with email: {}", userSignUpDTO.getEmail());
        return new ResponseEntity<>(new ApiResponse<>(userReturnDTO), HttpStatus.CREATED);
    }

    @GetMapping(path = "/getAllUsers")
    public ResponseEntity<ApiResponse<List<UserReturnDTO>>> getAllUsers() {
        log.info("Received request to get all users");
        ApiResponse<List<UserReturnDTO>> response = new ApiResponse<>(userServiceImpl.getAllUsers());
        log.info("Returning all users");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
