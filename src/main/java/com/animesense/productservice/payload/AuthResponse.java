package com.animesense.productservice.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private int userId;
    private String firstName;
    private String lastName;
    private String username;
    private long phoneNumber;
    private String email;
    private String password;
    private LocalDateTime userCreatedAt;
    private LocalDateTime userLastUpdated;
    private String userRole;
    private Date dateOfBirth;
}
