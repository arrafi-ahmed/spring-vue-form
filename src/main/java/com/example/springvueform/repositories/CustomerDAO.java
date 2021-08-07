package com.example.springvueform.repositories;

import com.example.springvueform.models.Customer;
import org.springframework.http.ResponseEntity;

public interface CustomerDAO {
    ResponseEntity<String> addCustomer(Customer customer);
}
