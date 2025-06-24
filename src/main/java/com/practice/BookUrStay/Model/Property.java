package com.practice.BookUrStay.Model;

import com.practice.BookUrStay.Model.Enum.PropertyType;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Property")
public class Property extends BaseModel {

    @NotBlank
    @Size(max = 100)
    private String propertyName;

    @NotNull
    private PropertyType propertyType;

    @Size(max = 500)
    private String description;

    @NotBlank
    @Size(max = 200)
    private String address;

    @NotBlank
    @Size(max = 50)
    private String city;

    @NotBlank
    @Size(max = 50)
    private String state;

    @NotBlank
    @Size(max = 50)
    private String country;

    @NotNull
    @PositiveOrZero
    private double pricePerNight;

    @NotNull
    @Min(1)
    private int maxGuests;

    @NotNull
    private boolean isAvailable;

    @NotBlank
    private String propertyEmail;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @OneToOne(optional = false)
    @JoinColumn(name = "manager_id", nullable = false)
    private User manager;

}
