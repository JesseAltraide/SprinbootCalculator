package com.example.Calculator.Service;

import com.example.Calculator.Database.CalculatorDatabase;
import com.example.Calculator.Database.UserDatabase;
import com.example.Calculator.model.Calculator;
import com.example.Calculator.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CalculatorService {

    @Autowired
    CalculatorDatabase calculatorDatabase;

    @Autowired
    UserDatabase userDatabase;

    public ResponseEntity<Double> calculate(Double num1, Double num2, String operand, Integer userId){

        Double result = (double) 0;


        try{


            Optional<User> user = userDatabase.findById(userId);

            result = switch (operand) {
                case "+" -> num1 + num2;
                case "-" -> num1 - num2;
                case "*" -> num1 * num2;
                case "/" -> num1 / num2;
                default -> throw new Exception("Invalid operand");
            };

            Calculator calculator = new Calculator(num1,num2,operand,result);
            calculator.setUser(user.get());
            calculatorDatabase.save(calculator);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<List<Calculator>> getAllCalculations(Integer userId){

        List<Calculator> calculator = new ArrayList<>();

        try{
            Optional<User> user = userDatabase.findById(userId);
            if(!user.isPresent()){
                return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

            calculator = calculatorDatabase.findByuser(user.get());
            return new ResponseEntity<>(calculator, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
        }

        return new ResponseEntity<>(calculator, HttpStatus.EXPECTATION_FAILED);
    }
}
