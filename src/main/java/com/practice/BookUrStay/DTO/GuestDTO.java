package com.practice.BookUrStay.DTO;

import com.practice.BookUrStay.Model.Enum.Gender;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class GuestDTO {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Gender cannot be blank")
    private String gender;

    @Size(min = 10, max = 10, message = "Phone number must be 10 characters")
    private String phoneNumber;

    @NotNull(message = "Age cannot be blank")
    private Integer age;

    @NotBlank(message = "Address cannot be blank")
    private String address;

    @NotBlank(message = "City cannot be blank")
    private String city;

    private String UserEmail; // Guest is linked to a User entity, this can be used to identify the user associated with the guest
    private String UserName; // Optional: to include the user's name for better identification

}
