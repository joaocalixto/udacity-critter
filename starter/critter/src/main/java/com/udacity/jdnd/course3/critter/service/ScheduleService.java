package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.repository.ScheduleRepository;
import com.udacity.jdnd.course3.critter.repository.entities.pet.Pet;
import com.udacity.jdnd.course3.critter.repository.entities.schedule.Schedule;
import com.udacity.jdnd.course3.critter.repository.entities.user.Employee;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Pet> findAllPetsIds(List<Long> petIds){
        return petRepository.findAllById(petIds);
    }

    public List<Employee> findAllEmployeesIds(List<Long> employeeIds){
        return employeeRepository.findAllById(employeeIds);
    }

    public Schedule save(Schedule schedule){

        Schedule scheduleSaved = scheduleRepository.save(schedule);

        scheduleSaved.getPets().forEach(pet -> {
            pet.getSchedulePets().add(schedule);
        });

        schedule.getEmployees().forEach(employee -> {
            employee.getScheduleEmployees().add(schedule);
        });

        petRepository.saveAll(scheduleSaved.getPets());
        employeeRepository.saveAll(scheduleSaved.getEmployees());

        return scheduleSaved;
    }

    public Schedule findById(Long scheduleId){
        return scheduleRepository.findById(scheduleId).get();
    }

    public List<Schedule> findAll(){
        return scheduleRepository.findAll();
    }

    public List<Schedule> getScheduleForPet(Long petId){
        return scheduleRepository.findByPetsId(petId);
    }


}
