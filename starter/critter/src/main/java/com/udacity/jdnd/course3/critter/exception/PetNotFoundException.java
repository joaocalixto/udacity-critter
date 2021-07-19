package com.udacity.jdnd.course3.critter.exception;

public class PetNotFoundException extends Exception {

    public PetNotFoundException(Long petId) {
        super("Pet Not Found with id = " + petId);
    }
}
