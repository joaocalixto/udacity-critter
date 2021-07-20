package com.udacity.jdnd.course3.critter.repository.entities.user;

import com.udacity.jdnd.course3.critter.repository.entities.schedule.Schedule;
import lombok.Data;
import org.dom4j.tree.AbstractEntity;
import org.hibernate.annotations.ManyToAny;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Entity
@Data
public class Employee extends User{

    @ElementCollection(targetClass = EmployeeSkill.class)
    @CollectionTable
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> skills;

    @ElementCollection(targetClass = DayOfWeek.class)
    @CollectionTable
    @Enumerated(EnumType.STRING)
    private Set<DayOfWeek> daysAvailable;

    @ManyToMany(targetEntity = Schedule.class)
    @JoinTable(name = "schedule_employees", joinColumns = @JoinColumn(name = "schedule_id"))
    private List<Schedule> scheduleEmployees;

}
