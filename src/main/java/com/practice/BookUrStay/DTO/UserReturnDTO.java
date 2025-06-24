package com.practice.BookUrStay.DTO;

import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class UserReturnDTO {

    private String username;

    private String email;

    private String phoneNumber;

    private PropertyDTO managedProperty;

    private Set<PropertyDTO> ownedProperties = new HashSet<>();

    // public void setOwnedProperties(Set<PropertyDTO> ownedProperties) {
    //     if (ownedProperties == null) {
    //         this.ownedProperties = new HashSet<>();
    //     } else {
    //         this.ownedProperties = ownedProperties;
    //     }
    // }
}
