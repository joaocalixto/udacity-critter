package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.entities.pet.Pet;
import com.udacity.jdnd.course3.critter.repository.entities.user.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Pet save(Pet pet){

        Pet save = petRepository.save(pet);
        if(pet.getUser() != null){

            Customer customer = (Customer) pet.getUser();
            customer.getPets().add(save);
            customerRepository.save(customer);
        }
       return save;
    }

    public Pet findById(Long petId){
        return petRepository.findById(petId).get();
    }

    public List<Pet> findAllPets(){
        return petRepository.findAll();
    }

    public List<Pet> findByOwnerId(Long ownerId){
        return petRepository.findByUserId(ownerId);
    }

    public List<Pet> findPets(List<Long> pedtsIds){

        return petRepository.findAllById(pedtsIds);
    }


}
