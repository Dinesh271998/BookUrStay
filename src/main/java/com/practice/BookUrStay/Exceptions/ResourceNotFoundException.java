package com.practice.BookUrStay.Exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message) {
        super(message);
        log.warn("ResourceNotFoundException thrown: {}", message);
    }
}
