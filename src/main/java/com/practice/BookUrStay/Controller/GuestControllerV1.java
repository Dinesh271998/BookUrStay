package com.practice.BookUrStay.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.practice.BookUrStay.Advices.ApiResponse;
import com.practice.BookUrStay.DTO.GuestDTO;
import com.practice.BookUrStay.Service.GuestServiceImpl;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/guests")
public class GuestControllerV1 {

    private final GuestServiceImpl guestServiceImpl;

    private GuestControllerV1(GuestServiceImpl guestServiceImpl) {
        this.guestServiceImpl = guestServiceImpl;
    }

    @PostMapping(path = "createGuest")
    public ResponseEntity<ApiResponse<GuestDTO>> postMethodName(@RequestBody GuestDTO guestDTO) {
        // Call the service method to create a guest
        GuestDTO createdGuest = guestServiceImpl.createGuest(guestDTO);

        // Return a success message or the created guest details
        return new ResponseEntity<>(new ApiResponse<>(createdGuest), HttpStatus.CREATED);
    }
}
