package com.practice.BookUrStay.Service;

import com.practice.BookUrStay.DTO.PropertyDTO;
import com.practice.BookUrStay.DTO.UserReturnDTO;
import com.practice.BookUrStay.DTO.UserSignUpDTO;
import com.practice.BookUrStay.Exceptions.ResourceAlreadyExistsException;
import com.practice.BookUrStay.Exceptions.ResourceNotFoundException;
import com.practice.BookUrStay.Mapper.PropertyMapper;
import com.practice.BookUrStay.Mapper.UserMapper;
import com.practice.BookUrStay.Model.User;
import com.practice.BookUrStay.Repository.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserReturnDTO getUserByEmail(String email) {
        logger.info("Fetching user by email: {}", email);
        if (email == null || email.isEmpty()) {
            logger.warn("Email cannot be null or empty");
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        // fetching the user from the database using the repository
        Optional<User> savedUser = userRepository.findByEmail(email);
        if (savedUser.isEmpty()) {
            logger.warn("User with email {} not found", email);
            throw new ResourceNotFoundException("User with email " + email + " not found");
        }
        logger.info("User found: with email {} ", email);
        // Convert User entity to UserReturnDTO
        return UserMapper.UserToDto(savedUser.get());
    }

    @Override
    public UserReturnDTO createUser(UserSignUpDTO userSignUpDTO) {
        logger.info("Creating user with email: {}", userSignUpDTO.getEmail());
        if (userSignUpDTO.getEmail() == null || userSignUpDTO.getEmail().isEmpty()) {
            logger.warn("Email cannot be null or empty");
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        if (!userSignUpDTO.getPassword().equals(userSignUpDTO.getConfirmPassword())) {
            logger.warn("Password and Confirm Password do not match");
            throw new IllegalArgumentException("Password and Confirm Password do not match");
        }

        // Check if the user already exists
        Optional<User> existingUser = userRepository.findByEmail(userSignUpDTO.getEmail());
        if (existingUser.isPresent()) {
            logger.warn("User with email {} already exists", userSignUpDTO.getEmail());
            throw new ResourceAlreadyExistsException("User with email " + userSignUpDTO.getEmail() + " already exists");
        }
        logger.info("User with email {} does not exist, proceeding to create a new user", userSignUpDTO.getEmail());
        // Convert DTO to User entity and save it
        User user = UserMapper.DtoToUser(userSignUpDTO);
        User savedUser = userRepository.save(user);
        return UserMapper.UserToDto(savedUser);
    }

    @Override
    public UserReturnDTO updateUser(String email, UserSignUpDTO userSignUpDTO) {
        logger.info("Updating user with email: {}", email);
        if (email == null || email.isEmpty()) {
            logger.warn("Email cannot be null or empty");
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        Optional<User> existingUserOpt = userRepository.findByEmail(email);
        if (existingUserOpt.isEmpty()) {
            logger.warn("User with email {} not found", email);
            throw new ResourceNotFoundException("User with email " + email + " not found");
        }

        User existingUser = existingUserOpt.get();

        // Update fields from DTO to entity
        existingUser = UserMapper.DtoToUser(userSignUpDTO);

        User updatedUser = userRepository.save(existingUser);
        logger.info("User with email {} updated successfully", email);
        return UserMapper.UserToDto(updatedUser);
    }

    @Override
    public void deleteUser(String email) {
    }

    @Override
    public List<UserReturnDTO> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream()
                .map(user -> UserMapper.UserToDto(user))
                .toList();
    }

    @Override
    public UserReturnDTO patchUser(String email, UserSignUpDTO userSignUpDTO) {
        logger.info("Patching user with email: {}", email);
        if (email == null || email.isEmpty()) {
            logger.warn("Email cannot be null or empty");
            throw new IllegalArgumentException("Email cannot be null or empty");
        }

        Optional<User> existingUserOpt = userRepository.findByEmail(email);
        if (existingUserOpt.isEmpty()) {
            logger.warn("User with email {} not found", email);
            throw new ResourceNotFoundException("User with email " + email + " not found");
        }

        User existingUser = existingUserOpt.get();

        // Patch basic fields
        if (userSignUpDTO.getUsername() != null) {
            existingUser.setUsername(userSignUpDTO.getUsername());
        }
        if (userSignUpDTO.getPassword() != null) {
            existingUser.setPassword(userSignUpDTO.getPassword());
        }
        if (userSignUpDTO.getPhoneNumber() != null) {
            existingUser.setPhoneNumber(userSignUpDTO.getPhoneNumber());
        }
        if (userSignUpDTO.getEmail() != null) {
            existingUser.setEmail(userSignUpDTO.getEmail());
            // Ensure email is unique
        }

        // Patch owned properties (replace list)
        if (userSignUpDTO.getOwnedProperties() != null) {
            // existingUser.getOwnedProperties().clear();
            for (PropertyDTO propertyDTO : userSignUpDTO.getOwnedProperties()) {
                existingUser.getOwnedProperties().add(PropertyMapper.DtoToProperty(propertyDTO));
            }
        }

        // Patch managed property
        if (userSignUpDTO.getManagedProperty() != null) {
            existingUser.setManagedProperty(
                    PropertyMapper.DtoToProperty(userSignUpDTO.getManagedProperty()));
        }

        User savedUser = userRepository.save(existingUser);
        logger.info("User with email {} patched successfully", email);
        return UserMapper.UserToDto(savedUser);
    }
}
