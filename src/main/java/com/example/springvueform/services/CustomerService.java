package com.example.springvueform.services;

import com.example.springvueform.models.Customer;
import com.example.springvueform.repositories.CustomerDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
public class CustomerService {
    @Autowired
    CustomerDAOImpl customerDAOImpl;

    public ResponseEntity<String> addCustomer(Customer customer, MultipartFile multipartFile) throws IOException {
        multipartFile.transferTo(new File("C:\\Users\\Raf\\Desktop\\spring-vue-form\\src\\main\\resources\\static" +
                "\\uploads\\" + multipartFile.getOriginalFilename()));
        customer.setProfileImage(multipartFile.getOriginalFilename());
        return customerDAOImpl.addCustomer(customer);
    }
}
