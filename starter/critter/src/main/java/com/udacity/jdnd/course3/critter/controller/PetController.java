package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.controller.dto.PetDTO;
import com.udacity.jdnd.course3.critter.exception.OwnerNotFoundException;
import com.udacity.jdnd.course3.critter.repository.entities.pet.Pet;
import com.udacity.jdnd.course3.critter.repository.entities.user.Customer;
import com.udacity.jdnd.course3.critter.service.CustomerService;
import com.udacity.jdnd.course3.critter.service.PetService;
import lombok.SneakyThrows;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {

    @Autowired
    private PetService petService;

    @Autowired
    private CustomerService customerService;

    @PostMapping
    @SneakyThrows
    public PetDTO savePet(@RequestBody PetDTO petDTO) {

        Pet pet = convertPetDTOtoEntity(petDTO);

        return convertEntityToPetDTO(petService.save(pet));
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {

        return convertEntityToPetDTO(petService.findById(petId));
    }

    @GetMapping
    public List<PetDTO> getPets(){

        return petService.findAllPets().stream().map(pet -> convertEntityToPetDTO(pet)).collect(Collectors.toList());
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {

        return petService.findByOwnerId(ownerId).stream().map(pet -> convertEntityToPetDTO(pet)).collect(Collectors.toList());
    }

    public static PetDTO convertEntityToPetDTO(Pet plant){

        PetDTO petDTO = new PetDTO();
        if(petDTO != null) {
            BeanUtils.copyProperties(plant, petDTO);
            if (plant.getUser() != null){
                petDTO.setOwnerId(plant.getId());
            }
        }
        return petDTO;
    }

    public Pet convertPetDTOtoEntity(PetDTO petDTO) throws OwnerNotFoundException {
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO, pet);

        if (petDTO.getOwnerId() != 0){
            Customer customer = customerService.findById(petDTO.getOwnerId());
            pet.setUser(customer);
        }


        return pet;
    }
}
