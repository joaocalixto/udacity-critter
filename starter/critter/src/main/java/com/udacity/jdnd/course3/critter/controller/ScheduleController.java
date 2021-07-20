package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.controller.dto.PetDTO;
import com.udacity.jdnd.course3.critter.controller.dto.ScheduleDTO;
import com.udacity.jdnd.course3.critter.exception.OwnerNotFoundException;
import com.udacity.jdnd.course3.critter.repository.entities.pet.Pet;
import com.udacity.jdnd.course3.critter.repository.entities.schedule.Schedule;
import com.udacity.jdnd.course3.critter.repository.entities.user.Customer;
import com.udacity.jdnd.course3.critter.repository.entities.user.Employee;
import com.udacity.jdnd.course3.critter.service.ScheduleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Schedules.
 */
@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping
    public ScheduleDTO createSchedule(@RequestBody ScheduleDTO scheduleDTO) {

        Schedule schedule = convertScheduleDTOtoEntity(scheduleDTO);

        return convertEntityToScheduleDTO(scheduleService.save(schedule));
    }

    @GetMapping
    public List<ScheduleDTO> getAllSchedules() {

        return scheduleService.findAll().stream().map(schedule -> convertEntityToScheduleDTO(schedule)).collect(Collectors.toList());
    }

    @GetMapping("/pet/{petId}")
    public List<ScheduleDTO> getScheduleForPet(@PathVariable long petId) {

        return scheduleService.getScheduleForPet(petId).stream().map(schedule -> convertEntityToScheduleDTO(schedule)).collect(Collectors.toList());
    }

    @GetMapping("/employee/{employeeId}")
    public List<ScheduleDTO> getScheduleForEmployee(@PathVariable long employeeId) {
        return scheduleService.getScheduleForEmployee(employeeId).stream().map(schedule -> convertEntityToScheduleDTO(schedule)).collect(Collectors.toList());
    }

    @GetMapping("/customer/{customerId}")
    public List<ScheduleDTO> getScheduleForCustomer(@PathVariable long customerId) {
        return scheduleService.getScheduleForCustomer(customerId).stream().map(schedule -> convertEntityToScheduleDTO(schedule)).collect(Collectors.toList());
    }

    public static ScheduleDTO convertEntityToScheduleDTO(Schedule schedule){

        ScheduleDTO scheduleDTO = new ScheduleDTO();
        if(schedule != null) {
            BeanUtils.copyProperties(schedule, scheduleDTO);

            if(schedule.getPets() != null){
                scheduleDTO.setPetIds(schedule.getPets().stream().map(pet -> pet.getId()).collect(Collectors.toList()));
            }

            if(schedule.getEmployees() != null){
                scheduleDTO.setEmployeeIds(schedule.getEmployees().stream().map(employee -> employee.getId()).collect(Collectors.toList()));
            }
        }
        return scheduleDTO;
    }

    public Schedule convertScheduleDTOtoEntity(ScheduleDTO scheduleDTO) {

        Schedule schedule = new Schedule();
        BeanUtils.copyProperties(scheduleDTO, schedule);

        if (scheduleDTO.getPetIds() != null){
            List<Pet> allPetsIds = scheduleService.findAllPetsIds(scheduleDTO.getPetIds());
            schedule.setPets(allPetsIds);
        }
        if(scheduleDTO.getEmployeeIds() != null){
            List<Employee> allEmployeesIds = scheduleService.findAllEmployeesIds(scheduleDTO.getEmployeeIds());
            schedule.setEmployees(allEmployeesIds);
        }


        return schedule;
    }
}
