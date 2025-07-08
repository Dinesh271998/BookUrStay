package com.practice.BookUrStay.Security;

import com.practice.BookUrStay.Model.User;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtTokenService {

    public String generateToken(User user);

    public String extractUsername(String token);

    public boolean isTokenValid(String token, UserDetails userDetails);
}
