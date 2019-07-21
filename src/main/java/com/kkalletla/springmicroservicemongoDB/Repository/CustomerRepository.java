package com.kkalletla.springmicroservicemongoDB.Repository;

import com.kkalletla.springmicroservicemongoDB.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    List<Customer> findByFirstName(String firstName);
    List<Customer> findByLastName(String lastName);
    //List<Customer> findByFirstLast(String firstName, String lastName);
}
