package com.udacity.jdnd.course3.critter.exception;

public class EmployeeNotFoundException extends Exception {

    public EmployeeNotFoundException(Long employeeId) {
        super("Employee Not Found with id = " + employeeId);
    }
}
