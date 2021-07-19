package com.udacity.jdnd.course3.critter.repository.entities.pet;

import com.udacity.jdnd.course3.critter.repository.entities.schedule.Schedule;
import com.udacity.jdnd.course3.critter.repository.entities.user.User;
import lombok.Data;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Pet {

    @Id
    @GeneratedValue
    private Long id;

    private PetType type;
    @Nationalized
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id") //many plants can belong to one delivery
    private User user;

    @ManyToMany(mappedBy = "pets")
    private List<Schedule> schedules;

    private LocalDate birthDate;
    private String notes;
}
