package com.example.Calculator.Database;

import com.example.Calculator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDatabase extends JpaRepository<User, Integer> {

    Optional<User> findById(Integer userId);
    Optional<User> findByEmail(String email);
}
