package com.practice.BookUrStay.Service;

import com.practice.BookUrStay.DTO.PropertyDTO;
import com.practice.BookUrStay.Exceptions.ResourceAlreadyExistsException;
import com.practice.BookUrStay.Exceptions.ResourceNotFoundException;
import com.practice.BookUrStay.Mapper.PropertyMapper;
import com.practice.BookUrStay.Model.Property;
import com.practice.BookUrStay.Repository.PropertyRepository;
import com.practice.BookUrStay.Repository.UserRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PropertyServiceImpl implements PropertyService {

    private final UserRepository userRepository;

    private final PropertyRepository propertyRepository;

    public PropertyServiceImpl(PropertyRepository propertyRepository, UserRepository userRepository) {
        this.propertyRepository = propertyRepository;
        this.userRepository = userRepository;
    }

    @Override
    public PropertyDTO createProperty(PropertyDTO propertyDTO) {
        if (propertyDTO.getPropertyEmail() == null || propertyDTO.getPropertyEmail().isEmpty()) {
            log.warn("Property email cannot be null or empty");
            throw new IllegalArgumentException("Property email cannot be null or empty");
        }

        Optional<Property> existingProperty = propertyRepository.findByPropertyEmail(propertyDTO.getPropertyEmail());
        if (existingProperty.isPresent()) {
            log.info("property already exists with given email: {}", propertyDTO.getPropertyEmail());
            throw new ResourceAlreadyExistsException(
                    "Property with email " + propertyDTO.getPropertyEmail() + " already exists");
        }

        Property property = PropertyMapper.DtoToProperty(propertyDTO);
        property.setOwner(userRepository.findByEmail(propertyDTO.getOwnerEmail())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Owner not found with email: " + propertyDTO.getOwnerEmail())));

        property.setManager(userRepository.findByEmail(propertyDTO.getManagerEmail())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Manager not found with email: {}" + propertyDTO.getManagerEmail())));

        propertyRepository.save(property);
        PropertyDTO savedPropertyDTO = PropertyMapper.PropertyToDto(property);
        log.info("Property created with email: {}", propertyDTO.getPropertyEmail());
        return savedPropertyDTO;
    }

    @Override
    public PropertyDTO getPropertyDetails(String propertyEmail) {
        if (propertyEmail == null || propertyEmail.isEmpty()) {
            log.warn("Property email cannot be null or empty");
            throw new IllegalArgumentException("Property email cannot be null or empty");
        }

        Optional<Property> property = propertyRepository.findByPropertyEmail(propertyEmail);
        if (property.isEmpty()) {
            log.warn("Property with email {} not found", propertyEmail);
            throw new ResourceNotFoundException("Property with email " + propertyEmail + " not found");
        }

        PropertyDTO propertyDTO = PropertyMapper.PropertyToDto(property.get());
        log.info("Property found with email: {}", propertyEmail);
        return propertyDTO;
    }

    @Override
    public void updatePropertyDetails(String propertyEmail, PropertyDTO propertyDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updatePropertyDetails'");
    }

    @Override
    public void deleteProperty(String propertyEmail) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteProperty'");
    }

    @Override
    public List<PropertyDTO> getAllProperties() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllProperties'");
    }
}
