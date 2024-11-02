package com.aviroop.customerservice.controller;

import com.aviroop.customerservice.dto.CustomerLoginRequest;
import com.aviroop.customerservice.dto.CustomerRegisterRequest;
import com.aviroop.customerservice.dto.CustomerResponse;
import com.aviroop.customerservice.model.Customer;
import com.aviroop.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody CustomerRegisterRequest customerRegisterRequest) throws Exception {
        customerService.registerUser(customerRegisterRequest);
    }

    @PostMapping("/login")
    public String createProduct(@RequestBody CustomerLoginRequest customerRegisterRequest) {
        return customerService.loginUser(customerRegisterRequest);
    }

    @GetMapping("/{customerId}")
    public Customer getSpecificProduct(@PathVariable(value = "customerId") String customerId) {
        return customerService.getSpecificCustomer(customerId);
    }
}
