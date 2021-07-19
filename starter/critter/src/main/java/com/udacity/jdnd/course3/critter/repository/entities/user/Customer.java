package com.udacity.jdnd.course3.critter.repository.entities.user;

import com.udacity.jdnd.course3.critter.repository.entities.pet.Pet;
import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
public class Customer extends User{

    private String phoneNumber;
    private String notes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private List<Pet> pets;
}
