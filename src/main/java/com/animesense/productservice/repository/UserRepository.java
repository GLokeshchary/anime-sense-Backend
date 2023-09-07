package com.animesense.productservice.repository;

import com.animesense.productservice.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String fullName);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}
