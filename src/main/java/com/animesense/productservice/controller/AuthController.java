package com.animesense.productservice.controller;

import com.animesense.productservice.exception.UserNotFoundException;
import com.animesense.productservice.models.User;
import com.animesense.productservice.payload.AuthRequest;
import com.animesense.productservice.payload.AuthResponse;
import com.animesense.productservice.payload.LoginResponse;
import com.animesense.productservice.payload.RegisterRequest;
import com.animesense.productservice.service.JwtService;
import com.animesense.productservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String addNewUser(@RequestBody RegisterRequest registerRequest) {
        return userService.saveUser(registerRequest);
    }
    @PostMapping("/updateUser/{userId}")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse updateUserByUserId(@RequestBody User user,@PathVariable int userId) throws UserNotFoundException {
        return userService.updateUserByUserId(userId,user);
    }
    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse getUserById(@PathVariable int userId) throws UserNotFoundException {
        return userService.getUserByUserId(userId);
    }
    @DeleteMapping("/deleteUser/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteUserByUserId(@PathVariable int userId) throws UserNotFoundException {
        return userService.deleteUserByUserId(userId);
    }
    @PostMapping("/authenticate")
    public LoginResponse authenticateAndGetToken(@RequestBody AuthRequest authRequest){
        return userService.authenticateUserByUserNameAndPassword(authRequest.getUsername(),authRequest.getPassword());
    }
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
}
