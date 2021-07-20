package com.udacity.jdnd.course3.critter.repository.entities.schedule;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.udacity.jdnd.course3.critter.repository.entities.pet.Pet;
import com.udacity.jdnd.course3.critter.repository.entities.user.Employee;
import com.udacity.jdnd.course3.critter.repository.entities.user.EmployeeSkill;
import lombok.Getter;
import lombok.Setter;
import org.dom4j.tree.AbstractEntity;
import org.hibernate.Hibernate;

import javax.annotation.Nullable;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
public class Schedule {

   @Id
   @GeneratedValue
    private Long id;

    @ManyToMany(mappedBy = "scheduleEmployees")
    private List<Employee> employees;

    @ManyToMany(mappedBy = "schedulePets")
    private List<Pet> pets;

    @NotNull
    private LocalDate date;

    @ElementCollection(targetClass = EmployeeSkill.class)
    @Enumerated(EnumType.STRING)
    private Set<EmployeeSkill> activities;


   public List<Employee> getEmployees() {
    Hibernate.initialize(employees);
    return employees;
   }

   public void setEmployees(List<Employee> employees) {
    this.employees = employees;
   }

   public List<Pet> getPets() {
    Hibernate.initialize(pets);
    return pets;
   }

   public void setPets(List<Pet> pets) {
    this.pets = pets;
   }

 public Long getId() {
  return id;
 }

 public void setId(Long id) {
  this.id = id;
 }

 public LocalDate getDate() {
  return date;
 }

 public void setDate(LocalDate date) {
  this.date = date;
 }

 public Set<EmployeeSkill> getActivities() {
  return activities;
 }

 public void setActivities(Set<EmployeeSkill> activities) {
  this.activities = activities;
 }
}
