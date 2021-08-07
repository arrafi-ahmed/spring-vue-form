package com.example.springvueform.controllers;

import com.example.springvueform.models.Customer;
import com.example.springvueform.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @PostMapping("customer/add")
    public ResponseEntity<String> addCustomer(@RequestParam("profilePhoto") MultipartFile multipartFile, Customer customer) throws IOException {
        return customerService.addCustomer(customer, multipartFile);
    }
}
