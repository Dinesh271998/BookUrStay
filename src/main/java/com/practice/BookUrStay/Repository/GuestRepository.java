package com.practice.BookUrStay.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.practice.BookUrStay.Model.Guest;

public interface GuestRepository extends JpaRepository<Guest, Long> {

    // Additional query methods can be defined here if needed
    // For example, to find guests by email or name, etc.
    Optional<Guest> findByEmail(String email);
}
