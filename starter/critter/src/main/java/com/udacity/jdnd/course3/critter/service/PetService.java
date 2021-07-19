package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.entities.pet.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetRepository petRepository;

    public Pet save(Pet pet){

        return petRepository.save(pet);
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
}
