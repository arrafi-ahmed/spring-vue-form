package com.example.springvueform.repositories;

import com.example.springvueform.models.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public ResponseEntity<String> addCustomer(Customer customer) {
        String sql = "insert into customer(first_name, last_name, gender, dob, profile_image) values (?,?,?,?,?);";
        Object[] args = {customer.getFirstName(), customer.getLastName(), customer.getGender(), customer.getDob(), customer.getProfileImage()};

        int responseCheck = jdbcTemplate.update(sql, args);
        if (responseCheck == 1){
            return new ResponseEntity<>("Save Successful!", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Save Failed!", HttpStatus.BAD_GATEWAY);
        }
    }
}
