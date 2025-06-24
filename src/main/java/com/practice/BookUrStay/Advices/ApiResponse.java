package com.practice.BookUrStay.Advices;

import lombok.Data;

import java.time.LocalDateTime;

@Data
// This class is a placeholder for API response handling.
public class ApiResponse<T> {
    private LocalDateTime timeStamp;
    private T data;
    private ApiError error;

    public ApiResponse(ApiError error) {
        this.timeStamp = LocalDateTime.now();
        this.error = error;
    }

    public ApiResponse(T data) {
        this.timeStamp = LocalDateTime.now();
        this.data = data;
    }
}