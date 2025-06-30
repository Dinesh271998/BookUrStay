package com.practice.BookUrStay.Service;

import com.practice.BookUrStay.DTO.GuestDTO;

public interface GuestService {

    // Define methods for GuestService here, e.g.:
    GuestDTO createGuest(GuestDTO guestDTO);

    void deleteGuest(String email);

    // Additional methods can be added as needed

}
