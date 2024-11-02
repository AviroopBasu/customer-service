package com.aviroop.customerservice.service;

import com.aviroop.customerservice.dto.CustomerLoginRequest;
import com.aviroop.customerservice.dto.CustomerRegisterRequest;
import com.aviroop.customerservice.dto.CustomerResponse;
import com.aviroop.customerservice.model.Customer;
import com.aviroop.customerservice.repository.CustomerRepository;
import com.aviroop.customerservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private JwtUtil jwtUtil;

    public void registerUser(CustomerRegisterRequest customerRegisterRequest) throws Exception {

        if(customerRepository.existsById(customerRegisterRequest.getUserId())) {
            log.error("UserID Already exists, please try with a different user ID");
            //throw new Exception("userID Already exists");
        }
        Customer customer = Customer.builder()
                .name(customerRegisterRequest.getName())
                .email(customerRegisterRequest.getEmail())
                .address(customerRegisterRequest.getAddress())
                .id(customerRegisterRequest.getUserId())
                .password(customerRegisterRequest.getPassword())
                .build();
        customerRepository.save(customer);
        log.info("Customer : {} is saved", customer.getId());
    }

   public String loginUser(CustomerLoginRequest customerLoginRequest) {
        if(customerRepository.existsById(customerLoginRequest.getUserId())) {
            Optional<Customer> customer = customerRepository.findById(customerLoginRequest.getUserId());
            if(customer.isPresent()) {
                //Validate user ID and Password
                if(customer.get().getId().equals(customerLoginRequest.getUserId()) && customer.get().getPassword().equals(customerLoginRequest.getPassword())) {
                    return jwtUtil.generateToken(customerLoginRequest.getUserId());
                } else {
                    return "Incorrect userid/password";
                }
            }
        } else {
            return "User ID Not found";
        }
       return null;
   }

    public Customer getSpecificCustomer(String customerId) {
        return customerRepository.findById(customerId).get();
    }
}
