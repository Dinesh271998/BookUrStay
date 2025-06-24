package com.practice.BookUrStay.Service;

import com.practice.BookUrStay.DTO.UserReturnDTO;
import com.practice.BookUrStay.DTO.UserSignUpDTO;

import java.util.List;

public interface UserService {

    public UserReturnDTO getUserByEmail(String email);
    public UserReturnDTO createUser(UserSignUpDTO userSignUpDTO);
    public UserReturnDTO updateUser(String email, UserSignUpDTO userSignUpDTO);
    public void deleteUser(String email);
    public List<UserReturnDTO> getAllUsers();
    public UserReturnDTO patchUser(String email, UserSignUpDTO userSignUpDTO);
}
