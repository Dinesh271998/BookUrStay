package com.practice.BookUrStay.Security;

import com.practice.BookUrStay.Model.User;
import com.practice.BookUrStay.Repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.swing.text.html.Option;
import java.util.Optional;

public class CustomAppUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomAppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByEmail(username);

        if (userOptional.isPresent()){
            User savedUser = userOptional.get();
        }

        throw new UsernameNotFoundException("User with username " + username + " not found");
    }
}
