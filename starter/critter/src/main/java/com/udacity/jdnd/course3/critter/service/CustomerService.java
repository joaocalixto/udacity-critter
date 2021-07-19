package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.exception.OwnerNotFoundException;
import com.udacity.jdnd.course3.critter.exception.PetNotFoundException;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.entities.pet.Pet;
import com.udacity.jdnd.course3.critter.repository.entities.user.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer save(Customer customer){
        return customerRepository.save(customer);
    }

    public Customer findById(Long customerId) throws OwnerNotFoundException {
        return customerRepository.findById(customerId).orElseThrow(() -> new OwnerNotFoundException(customerId));
    }

    public Customer findOwnerByPetId(Long petId) throws PetNotFoundException {
        return customerRepository.findByPetsId(petId).orElseThrow(() -> new PetNotFoundException(petId));
    }

    public List<Customer> findAllCustomers(){
        return customerRepository.findAll();
    }
}
