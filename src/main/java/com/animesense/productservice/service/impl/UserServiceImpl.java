package com.animesense.productservice.service.impl;

import com.animesense.productservice.exception.UserNotFoundException;
import com.animesense.productservice.models.User;
import com.animesense.productservice.payload.AuthResponse;
import com.animesense.productservice.payload.LoginResponse;
import com.animesense.productservice.payload.RegisterRequest;
import com.animesense.productservice.repository.UserRepository;
import com.animesense.productservice.service.JwtService;
import com.animesense.productservice.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    @Override
    public List<User> getAllUsers() {
        List<User> users=userRepository.findAll();
        return users.stream().filter(user->user.getUserRole().equals("CUSTOMER")).toList();
    }

    @Override
    public String saveUser(RegisterRequest registerRequest) {
        boolean isUserExists = userRepository.existsByEmail(registerRequest.getEmail());
        if(isUserExists){
            return "Email already exists in DB";
        }
        String username=registerRequest.getFirstName()+registerRequest.getLastName();
        boolean isUserExistsByUsername= userRepository.existsByUsername(username);
        if(isUserExistsByUsername){
            return "Username already exists";
        }
        User user=modelMapper.map(registerRequest,User.class);
        user.setUsername(registerRequest.getFirstName()+registerRequest.getLastName());
        if(registerRequest.getPassword().equals(registerRequest.getReEnterPassword())){
            user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        }
        user.setUserCreatedAt(LocalDateTime.now());
        user.setUserRole("CUSTOMER");

        userRepository.save(user);
        return "Successfully Registered";
    }

    @Override
    public String deleteUserByUserId(int userId) throws UserNotFoundException {
        User user=userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User with userId "+userId+" Not Found"));
        if(user!=null){
            userRepository.delete(user);
            return "User with userId "+userId+" Deleted";
        }
        else {
            return "Unable to Delete The User with userId "+userId;
        }
    }

    @Override
    public AuthResponse updateUserByUserId(int userId, User user) throws UserNotFoundException {
        User user1=userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User with userId "+userId+" Not Found"));
        if(user1!=null){
            user1.setFirstName(user.getFirstName());
            user1.setLastName(user.getLastName());
            user1.setUsername(user.getFirstName()+user.getLastName());
            user1.setPhoneNumber(user.getPhoneNumber());
            user1.setEmail(user.getEmail());
            user1.setPassword(passwordEncoder.encode(user.getPassword()));
            user1.setUserLastUpdated(LocalDateTime.now());
            user1.setDateOfBirth(user.getDateOfBirth());
            userRepository.save(user1);
            return modelMapper.map(user1, AuthResponse.class);
        }
        return null;
    }

    @Override
    public AuthResponse getUserByUserId(int userId) throws UserNotFoundException {
        User user1=userRepository.findById(userId).orElseThrow(()->new UserNotFoundException("User with userId "+userId+" Not Found"));
        return  modelMapper.map(user1, AuthResponse.class);
    }

    @Override
    public LoginResponse authenticateUserByUserNameAndPassword(String username, String password) {
        boolean isUserExists = userRepository.existsByUsername(username);
        if(isUserExists){
            User user=userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException("User with "+username+" Not Found"));
            LoginResponse loginResponse=modelMapper.map(user, LoginResponse.class);
            boolean verifyPassword=passwordEncoder.matches(password,user.getPassword());
            if(verifyPassword){
                String jwtToken= jwtService.generateToken(username);
                loginResponse.setJwtToken(jwtToken);
                loginResponse.setLoginMessage("Logged In Successfully");
            }
            else {
                loginResponse.setJwtToken(null);
                loginResponse.setLoginMessage("Invalid Password");
            }
            return loginResponse;
        }
        else {
            LoginResponse loginResponse=new LoginResponse();
            loginResponse.setLoginMessage("Invalid Username");
            return loginResponse;
        }
    }
}
