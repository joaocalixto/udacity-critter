package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.exception.EmployeeNotFoundException;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.entities.user.Employee;
import com.udacity.jdnd.course3.critter.repository.entities.user.EmployeeSkill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee save(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee findById(Long employeeId){
        return employeeRepository.findById(employeeId).get();
    }

    public Employee setsAvailableDays(Set<DayOfWeek> avalibleDays, Long employeeId) throws EmployeeNotFoundException {

        Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new EmployeeNotFoundException(employeeId));

        employee.setDaysAvailable(avalibleDays);

        return employeeRepository.save(employee);
    }

    public List<Employee> findAllEmployees(){
        return employeeRepository.findAll();
    }

    public List<Employee> findEmployeesForService(Set<EmployeeSkill> skills, LocalDate date){

        DayOfWeek day = DayOfWeek.of(date.getDayOfWeek().getValue());

        List<Employee> distinctBySkillsInAndScheduleEmployeesDate = employeeRepository.findDistinctBySkillsIsInAndDaysAvailable(skills, day);

        List<Employee> collect = distinctBySkillsInAndScheduleEmployeesDate.stream().filter(employee -> employee.getSkills().containsAll(skills)).collect(Collectors.toList());

        return collect;
    }
}
