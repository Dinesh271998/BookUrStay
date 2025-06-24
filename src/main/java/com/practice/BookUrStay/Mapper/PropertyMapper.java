package com.practice.BookUrStay.Mapper;



import com.practice.BookUrStay.DTO.PropertyDTO;
import com.practice.BookUrStay.Model.Enum.PropertyType;
import com.practice.BookUrStay.Model.Property;

public class PropertyMapper {

    // private static UserRepository userRepository;
    // private static PropertyRepository propertyRepository;

    // public PropertyMapper(UserRepository userRepository, PropertyRepository
    // propertyRepository) {
    // this.userRepository = userRepository;
    // this.propertyRepository = propertyRepository;
    // }

    public static PropertyDTO PropertyToDto(Property property) {
        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setPropertyName(property.getPropertyName());
        propertyDTO.setDescription(property.getDescription());
        propertyDTO.setAddress(property.getAddress());
        propertyDTO.setPricePerNight(property.getPricePerNight());
        propertyDTO.setPropertyType(String.valueOf(property.getPropertyType()));
        propertyDTO.setOwnerEmail(property.getOwner().getEmail());
        propertyDTO.setManagerEmail(property.getManager().getEmail());
        propertyDTO.setCity(property.getCity());
        propertyDTO.setPropertyEmail(property.getPropertyEmail());
        propertyDTO.setState(property.getState());
        propertyDTO.setCreatedAt(property.getCreatedAt());
        propertyDTO.setUpdatedAt(property.getUpdatedAt());
        return propertyDTO;
    }

    public static Property DtoToProperty(PropertyDTO propertyDTO) {
        Property property = new Property();
        property.setPropertyName(propertyDTO.getPropertyName());
        property.setDescription(propertyDTO.getDescription());
        property.setAddress(propertyDTO.getAddress());
        property.setPricePerNight(propertyDTO.getPricePerNight());
        property.setPropertyType(PropertyType.valueOf(propertyDTO.getPropertyType()));
        property.setCity(propertyDTO.getCity());
        property.setPropertyEmail(propertyDTO.getPropertyEmail());
        property.setState(propertyDTO.getState());
        property.setMaxGuests(propertyDTO.getMaxGuests());
        property.setCountry(propertyDTO.getCountry());
        property.setAvailable(propertyDTO.getIsAvailable());
        // You may want to property createdAt and updatedAt if your entity supports it
        // property.setCreatedAt(propertyDTO.getCreatedAt());
        // property.setUpdatedAt(propertyDTO.getUpdatedAt());
        // Owner and Manager should be property in the service after fetching User entities
        return property;
    }
}
