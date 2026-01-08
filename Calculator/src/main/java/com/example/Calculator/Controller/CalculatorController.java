package com.example.Calculator.Controller;

import com.example.Calculator.Service.CalculatorService;
import com.example.Calculator.model.Calculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/calculator")
public class CalculatorController {

    @Autowired
    CalculatorService calculatorService;

    @PostMapping("/calculate")
    public ResponseEntity<Double> calculate (@RequestBody Double num1, Double num2, String operand, Integer userId){
        return calculatorService.calculate(num1,num2,operand,userId);
    }

    @GetMapping("/getUserCalculations")
    public ResponseEntity<List<Calculator>> getUserCalculations(@RequestParam Integer userId){
        return calculatorService.getAllCalculations(userId);
    }

}
