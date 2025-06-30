package com.practice.BookUrStay.Mapper;

import com.practice.BookUrStay.DTO.GuestDTO;
import com.practice.BookUrStay.Model.Guest;
import com.practice.BookUrStay.Model.Enum.Gender;

public class GuestMapper {

    // Method to convert GuestDTO to Guest entity
    public static Guest DtoToGuest(GuestDTO guestDTO) {
        if (guestDTO == null) {
            return null;
        }

        Guest guest = new Guest();
        guest.setName(guestDTO.getName());
        guest.setEmail(guestDTO.getEmail());
        guest.setGender(Gender.valueOf(guestDTO.getGender()));
        guest.setPhoneNumber(guestDTO.getPhoneNumber());
        guest.setAge(guestDTO.getAge());
        guest.setAddress(guestDTO.getAddress());
        guest.setCity(guestDTO.getCity());
        // Note: We do not set the user here, as Guest is linked to a User entity;
        // so we need to set the user later

        return guest;
    }

    public static GuestDTO GuestToDto(Guest guest) {
        if (guest == null) {
            return null;
        }

        GuestDTO guestDTO = new GuestDTO();
        guestDTO.setName(guest.getName());
        guestDTO.setEmail(guest.getEmail());
        guestDTO.setGender(String.valueOf(guest.getGender()));
        guestDTO.setPhoneNumber(guest.getPhoneNumber());
        guestDTO.setAge(guest.getAge());
        guestDTO.setAddress(guest.getAddress());
        guestDTO.setCity(guest.getCity());
        guestDTO.setUserEmail(guest.getUser() != null ? guest.getUser().getEmail() : null);
        guestDTO.setUserName(guest.getUser() != null ? guest.getUser().getUsername() : null);

        return guestDTO;
    }
}