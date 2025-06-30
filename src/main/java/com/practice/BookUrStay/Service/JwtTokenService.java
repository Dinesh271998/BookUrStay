package com.practice.BookUrStay.Service;

import com.practice.BookUrStay.Model.User;

public interface JwtTokenService {

    public String generateToken(User user);

    public String extractUsername(String token);
}
