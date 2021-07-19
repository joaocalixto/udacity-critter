package com.udacity.jdnd.course3.critter.exception;

public class OwnerNotFoundException extends Exception {

    public OwnerNotFoundException(Long ownerId) {
        super("Owner Not Found with id = " + ownerId);
    }
}
