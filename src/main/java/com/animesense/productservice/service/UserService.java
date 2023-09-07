package com.animesense.productservice.service;

import com.animesense.productservice.exception.UserNotFoundException;
import com.animesense.productservice.models.User;
import com.animesense.productservice.payload.AuthResponse;
import com.animesense.productservice.payload.LoginResponse;
import com.animesense.productservice.payload.RegisterRequest;

import java.util.List;

public interface UserService {
    public List<User> getAllUsers();
    public String saveUser(RegisterRequest registerRequest);

    public String deleteUserByUserId(int userId) throws UserNotFoundException;

    public AuthResponse updateUserByUserId(int userId, User user) throws UserNotFoundException;

    public AuthResponse getUserByUserId(int userId) throws UserNotFoundException;

    public LoginResponse authenticateUserByUserNameAndPassword(String username,String password);


}
