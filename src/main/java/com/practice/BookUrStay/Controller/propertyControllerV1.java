package com.practice.BookUrStay.Controller;

import com.practice.BookUrStay.Advices.ApiResponse;
import com.practice.BookUrStay.DTO.PropertyDTO;
import com.practice.BookUrStay.Service.PropertyServiceImpl;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/property")
@Slf4j
public class propertyControllerV1 {

    private final PropertyServiceImpl propertyServiceImpl;

    public propertyControllerV1(PropertyServiceImpl propertyServiceImpl) {
        this.propertyServiceImpl = propertyServiceImpl;
    }

    @PostMapping(path = "/createProperty")
    public ResponseEntity<ApiResponse<PropertyDTO>> createProperty(@Valid @RequestBody PropertyDTO propertyDTO) {
        log.info("Received request to create property with email: {}", propertyDTO.getPropertyEmail());
        PropertyDTO createdProperty = propertyServiceImpl.createProperty(propertyDTO);
        log.info("Property created successfully with email: {}", propertyDTO.getPropertyEmail());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(createdProperty));
    }

    @GetMapping(path = "/getPropertyDetails/{propertyEmail}")
    public ResponseEntity<ApiResponse<PropertyDTO>> getPropertyDetails(@PathVariable String propertyEmail) {
        log.info("Received request to get property details for email: {}", propertyEmail);
        PropertyDTO returnedPropertyDTO = propertyServiceImpl.getPropertyDetails(propertyEmail);
        log.info("Returning property details for email: {}", propertyEmail);
        return new ResponseEntity<>(new ApiResponse<>(returnedPropertyDTO), HttpStatus.OK);
    }
}
