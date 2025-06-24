package com.practice.BookUrStay.Exceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ResourceAlreadyExistsException extends RuntimeException {

    public ResourceAlreadyExistsException(String message) {
        super(message);
        log.warn("ResourceAlreadyExistsException thrown: {}", message);
    }
}
// This exception is thrown when a resource that is being created already exists.
// It extends RuntimeException, allowing it to be thrown without being declared in method signatures.