package com.practice.BookUrStay.DTO;

import jakarta.validation.constraints.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Locale;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PropertyDTO {

    @NotBlank(message = "Property name is required")
    @Size(max = 100, message = "Property name must not exceed 100 characters")
    private String propertyName;

    @NotBlank(message = "Property type is required")
    @Size(max = 50, message = "Property type must not exceed 50 characters")
    private String propertyType;

    @Size(max = 500, message = "Description must not exceed 500 characters")
    private String description;

    @NotBlank(message = "Address is required")
    @Size(max = 200, message = "Address must not exceed 200 characters")
    private String address;

    @NotBlank(message = "City is required")
    @Size(max = 50, message = "City must not exceed 50 characters")
    private String city;

    @NotBlank(message = "State is required")
    @Size(max = 50, message = "State must not exceed 50 characters")
    private String state;

    @NotBlank(message = "Country is required")
    @Size(max = 50, message = "Country must not exceed 50 characters")
    private String country;

    @NotBlank(message = "Owner is required")
    private String ownerEmail;

    @NotBlank(message = "Manager is required")
    private String managerEmail;

    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "\\d{10,15}", message = "Contact number must be between 10 and 15 digits")
    private String contactNumber;

    @NotBlank(message = "Property email is required")
    @Email(message = "Property email must be a valid email address")
    private String propertyEmail;

    @NotNull(message = "Price per night is required")
    @PositiveOrZero(message = "Price per night must be zero or positive")
    private Double pricePerNight;

    @NotNull(message = "Max guests is required")
    @Min(value = 1, message = "Max guests must be at least 1")
    private Integer maxGuests;

    @NotNull(message = "Availability must be specified")
    private Boolean isAvailable;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}