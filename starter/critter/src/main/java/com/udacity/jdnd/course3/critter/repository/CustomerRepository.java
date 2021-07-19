package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.repository.entities.pet.Pet;
import com.udacity.jdnd.course3.critter.repository.entities.user.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer>  findByPetsId(Long id);
}
