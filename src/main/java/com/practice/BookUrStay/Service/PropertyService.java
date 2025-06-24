package com.practice.BookUrStay.Service;

import java.util.List;

import com.practice.BookUrStay.DTO.PropertyDTO;

// This is a placeholder interface for PropertyService.
public interface PropertyService {

    public PropertyDTO createProperty(PropertyDTO propertyDTO);

    public PropertyDTO getPropertyDetails(String propertyEmail);

    public void updatePropertyDetails(String propertyEmail, PropertyDTO propertyDTO);

    public void deleteProperty(String propertyEmail);

    public List<PropertyDTO> getAllProperties();
}
// This interface defines the contract for property-related operations in the BookUrStay application.
// It includes methods for creating, retrieving, updating, and deleting properties, as well as fetching