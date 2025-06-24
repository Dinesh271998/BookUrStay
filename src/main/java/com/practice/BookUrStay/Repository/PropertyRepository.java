package com.practice.BookUrStay.Repository;

import com.practice.BookUrStay.Model.Property;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyRepository extends JpaRepository<Property, Long> {
    Optional<Property> findByPropertyEmail(String propertyEmail);
}
