package com.kkalletla.springmicroservicemongoDB.operations;

import com.kkalletla.springmicroservicemongoDB.Repository.CustomerRepository;
import com.kkalletla.springmicroservicemongoDB.entity.Customer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
@RequestMapping("/customer")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Operations {

    @Autowired
    CustomerRepository customerRepository;

    Logger logger = Logger.getLogger(Operations.class);

    @RequestMapping(value = "/customers", method = RequestMethod.GET)
    public List<Customer> getCustomers(@QueryParam("firstName") String firstName,
                                       @QueryParam("lastName") String lastName){

        logger.info("From getCustomers "+firstName+" "+lastName);

        if(firstName == null && lastName == null)
            return customerRepository.findAll();
        /*else if(firstName != null && lastName != null){
            return customerRepository.findByFirstLast(firstName,lastName);
        }*/
        else if(firstName != null){
            return customerRepository.findByFirstName(firstName);
        }
        else if(lastName != null){
            return customerRepository.findByLastName(lastName);
        }
        return null;
    }

    /*@GET
    @Path("/{name}")*/
    public List<Customer> getByFirstName(@PathParam("name") String name){
        List<Customer> customers = customerRepository.findByFirstName(name);
        customers.addAll(customerRepository.findByLastName(name));
        return customers;
    }

    @RequestMapping(value = "/customers", method = RequestMethod.POST)
    public Customer addCustomer(@RequestParam("firstName") String firstName,
                                @RequestParam("lastName") String lastName){
        logger.info("Inserting to Customers: "+firstName+" "+lastName);
        Customer customer = new Customer(firstName,lastName);
        return customerRepository.insert(customer);
    }


    @RequestMapping(value = "/customers", method = RequestMethod.DELETE)
    public String deleteCustomers(@QueryParam("firstName") String firstName,
                                       @QueryParam("lastName") String lastName){

        logger.info("From DeleteCustomers");
        if(firstName == null && lastName == null)
            return null;//customerRepository.findAll();
        /*else if(firstName != null && lastName != null){
            return customerRepository(firstName,lastName);
        }*/
        else {
            Customer customer = new Customer(firstName,lastName);
            customerRepository.delete(customer);
            return firstName+" "+lastName;
        }
    }

}
