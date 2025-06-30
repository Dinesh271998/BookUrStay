package com.practice.BookUrStay.Service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.practice.BookUrStay.DTO.GuestDTO;
import com.practice.BookUrStay.Exceptions.ResourceNotFoundException;
import com.practice.BookUrStay.Mapper.GuestMapper;
import com.practice.BookUrStay.Model.Guest;
import com.practice.BookUrStay.Repository.GuestRepository;
import com.practice.BookUrStay.Repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GuestServiceImpl implements GuestService {

    private final GuestRepository guestRepository;
    private final UserRepository userRepository;

    public GuestServiceImpl(GuestRepository guestRepository, UserRepository userRepository) {
        this.guestRepository = guestRepository;
        this.userRepository = userRepository;
    }

    @Override
    public GuestDTO createGuest(GuestDTO guestDTO) {
        log.info("Creating guest with email: {}", guestDTO.getEmail());
        if (guestDTO.getEmail() == null || guestDTO.getEmail().isEmpty()) {
            log.warn("Email cannot be null or empty");
            throw new IllegalArgumentException("Email cannot be null or empty");
        }
        Optional<Guest> existingGuest = guestRepository.findByEmail(guestDTO.getEmail());
        if (existingGuest.isPresent()) {
            log.warn("Guest with email {} already exists", guestDTO.getEmail());
            throw new IllegalArgumentException("Guest with email " + guestDTO.getEmail() + " already exists");
        }

        Guest guest = GuestMapper.DtoToGuest(guestDTO);
        guest.setUser(userRepository.findByEmail(guestDTO.getUserEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + guestDTO.getUserEmail())));

        guestRepository.save(guest);
        log.info("Guest created successfully with email: {}", guestDTO.getEmail());
        return GuestMapper.GuestToDto(guest);
    }

    @Override
    public void deleteGuest(String email) {
        log.info("Deleting guest with email: {}", email);
        // Logic to delete a guest by id
    }

    // Additional methods can be added as needed

}
