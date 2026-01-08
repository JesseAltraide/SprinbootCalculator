package com.example.Calculator.Database;

import com.example.Calculator.model.Calculator;
import com.example.Calculator.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CalculatorDatabase extends JpaRepository<Calculator, Integer> {

    List<Calculator> findByuser(User user);

}
