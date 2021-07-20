package com.udacity.jdnd.course3.critter.repository.entities.schedule;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.udacity.jdnd.course3.critter.repository.entities.pet.Pet;
import com.udacity.jdnd.course3.critter.repository.entities.user.Employee;
import com.udacity.jdnd.course3.critter.repository.entities.user.EmployeeSkill;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Embeddable
public class ScheduleKey {

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "pet_id")
    private Long petId;
}
