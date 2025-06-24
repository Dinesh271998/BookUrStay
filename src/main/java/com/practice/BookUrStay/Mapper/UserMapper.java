package com.practice.BookUrStay.Mapper;

import org.apache.catalina.core.PropertiesRoleMappingListener;

import com.practice.BookUrStay.DTO.PropertyDTO;
import com.practice.BookUrStay.DTO.UserReturnDTO;
import com.practice.BookUrStay.DTO.UserSignUpDTO;
import com.practice.BookUrStay.Model.Property;
import com.practice.BookUrStay.Model.User;
import com.practice.BookUrStay.Repository.PropertyRepository;
import com.practice.BookUrStay.Repository.UserRepository;

public class UserMapper {

    // private final PropertyRepository propertyRepository;
    // private final UserRepository userRepository
    //
    // public UserMapper(PropertyRepository propertyRepository, UserRepository
    // userRepository) {
    // this.propertyRepository = propertyRepository;
    // this.userRepository = userRepository;
    // }

    public static User DtoToUser(UserSignUpDTO signUpDTO) {
        User user = new User();
        user.setUsername(signUpDTO.getUsername());
        user.setEmail(signUpDTO.getEmail());
        user.setPassword(signUpDTO.getPassword());
        user.setPhoneNumber(signUpDTO.getPhoneNumber());
        return user;
    }

    public static UserReturnDTO UserToDto(User user) {
        UserReturnDTO returnDTO = new UserReturnDTO();
        returnDTO.setEmail(user.getEmail());
        returnDTO.setUsername(user.getUsername());
        returnDTO.setPhoneNumber(user.getPhoneNumber());

        // Assuming you have a method to convert properties to DTOs
        if (user.getOwnedProperties() != null) {
            user.getOwnedProperties().forEach(property -> {
                PropertyDTO propertyDTO = PropertyMapper.PropertyToDto(property);
                returnDTO.getOwnedProperties().add(propertyDTO);
            });
        }

        if (user.getManagedProperty() != null) {
            PropertyDTO managedPropertyDTO = PropertyMapper.PropertyToDto(user.getManagedProperty());
            returnDTO.setManagedProperty(managedPropertyDTO);
        }

        // if (user.getGuests() != null) {
        // for (Guest guest : user.getGuests()) {
        // GuestDTO guestDTO = new GuestDTO();
        // GuestMapper.GuestToDto(guestDTO, guest);
        // returnDTO.getGuests().add(guestDTO);
        // }

        // }
        return returnDTO;
    }

    public static User UserToUser(User existingUser, User NewUser) {
        existingUser.setUsername(NewUser.getUsername());
        existingUser.setEmail(NewUser.getEmail());
        existingUser.setPassword(NewUser.getPassword());
        existingUser.setPhoneNumber(NewUser.getPhoneNumber());
        // Add more fields as necessary
        return existingUser;
    }
}
